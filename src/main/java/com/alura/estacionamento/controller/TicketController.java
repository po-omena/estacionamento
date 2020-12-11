package com.alura.estacionamento.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.estacionamento.controller.form.TicketForm;
import com.alura.estacionamento.estrutura.Ticket;
import com.alura.estacionamento.estrutura.interna.StatusTicket;
import com.alura.estacionamento.repository.TicketRepository;

@Transactional
@RequestMapping(value = "/ticket")
@RestController
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;

//	@Autowired
//	private CarroRepository carroRepository;

	@SuppressWarnings("unchecked")
	@GetMapping
	public List<Ticket> pageGetTickets() {

		List<Ticket> tickets = ticketRepository.findAll();

		for (Ticket ticket : tickets) {
			ticket.setValor(ticket.calculaValorAtual());	
		}
		
		tickets = estruturaTickets(tickets);
		
		return tickets;
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity<List> criaTicket(@RequestBody TicketForm form, UriComponentsBuilder uriBuilder) {

		Ticket ticket = form.converter();
		ticketRepository.save(ticket);

		URI uri = uriBuilder.path("/tickets/{id}").buildAndExpand(ticket.getId()).toUri();
		return ResponseEntity.created(uri).body(ticket.imprimeTicketCliente());
	}

	@SuppressWarnings({ "unchecked" })
	@GetMapping("/aberto")
	public List<Ticket> pageAbertos() {

		List<Ticket> tickets = ticketRepository.findAll();
		tickets = getAbertos(tickets);

		tickets = estruturaTickets(tickets);

		return tickets;
	}

//	Retorna o ticket aberto dado a placa do carro
	@SuppressWarnings("unchecked")
	@GetMapping("/aberto/{placa}")
	public List<Ticket> pageTicketAbertoPorPlaca(@PathVariable String placa) {

		List<Ticket> abertos = ticketRepository.findByCarroPlaca(placa);
		abertos = getAbertos(abertos);
		abertos = estruturaTickets(abertos);

		return abertos;
	}

//	Retorna todos os tickets dado a placa de um veículo
	@SuppressWarnings("unchecked")
	@GetMapping("/{placa}")
	public List<Ticket> getPorPlaca(@PathVariable String placa) {

		List<Ticket> tickets = ticketRepository.findByCarroPlaca(placa);

		for (Ticket ticket : tickets) {
			ticket.setValor(ticket.calculaValorAtual());
		}

		tickets = estruturaTickets(tickets);
		
		return tickets;
	}

//	Fecha e calcula o valor do ticket aberto do veículo.
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/fecha/{placa}")
	public List fechaPorPlaca(@PathVariable String placa) {

		List<Ticket> tickets = getTicketAberto(placa);
		if (tickets.size() == 1) {

			Ticket ultimoTicket = tickets.get(tickets.size() - 1);
			ultimoTicket.fechaTicket();
			ticketRepository.save(ultimoTicket);

			return ultimoTicket.imprimeTicket();
		} else if (tickets.size() == 0) {

			List lista = new ArrayList<>();
			lista.add("Não existem tickets abertos registrados nesta placa.");

			return lista;
		}

		List retorno = new ArrayList<>();
		retorno.add("Existem mais de 1 tickets abertos, por favor fechar diretamente pelo Ticket ID.");
		retorno.add(tickets);

		return retorno;
	}

	
//Métodos úteis
	
	//Estrutura Tickets para impressão
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List estruturaTickets(List<Ticket> tickets) {

		List ticketsEstruturados = new ArrayList<>();
		for (Ticket ticket : tickets) {
			ticketsEstruturados.add(ticket.imprimeTicket());
		}
		return ticketsEstruturados;
	}
	
//	Retorna os tickets abertos de todo o estacionamento

	@SuppressWarnings("rawtypes")
	public List getAbertos(List<Ticket> tickets) {

		if (tickets.isEmpty()) {
			tickets = ticketRepository.findAll();
		}

		List<Ticket> abertos = new ArrayList<Ticket>();
		StatusTicket abertoEnum = StatusTicket.ABERTO;

		for (Ticket ticket : tickets) {
			if (ticket.getStatus().equals(abertoEnum)) {

				ticket.setValor(ticket.calculaValorAtual());
				abertos.add(ticket);
			}
		}
		System.out.println("Existem atualmente " + abertos.size() + " tickets abertos no estacionamento.");
		return abertos;
	}

	//	Retorna os tickets abertos por placa
	
	@SuppressWarnings("unchecked")
	public List<Ticket> getTicketAberto(@PathVariable String placa) {

		List<Ticket> abertos = ticketRepository.findByCarroPlaca(placa);
		abertos = getAbertos(abertos);

		return abertos;
	}
}
