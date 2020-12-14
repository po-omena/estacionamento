package com.alura.estacionamento.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
import com.alura.estacionamento.controller.manager.TicketManager;
import com.alura.estacionamento.estrutura.Ticket;
import com.alura.estacionamento.estrutura.interna.StatusTicket;
import com.alura.estacionamento.repository.TicketRepository;

@Transactional
@RequestMapping(value = "/ticket")
@RestController
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;

	TicketManager tm = new TicketManager();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping
	public List<Ticket> pageGetTickets() {

		List<Ticket> tickets = ticketRepository.findAll();
		int tamanho = tickets.size();
		
		for (Ticket ticket : tickets) {
			ticket.setValor(ticket.calculaValorAtual());
		}
		tickets = tm.estruturaTickets(tickets);
		List retorno = new ArrayList();
		retorno.add(String.format("Existem %d tickets registrados", tamanho));
		retorno.add("-----------------------------");
		retorno.add(tickets);

		return retorno;
	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity<List> criaTicket(@RequestBody TicketForm form, UriComponentsBuilder uriBuilder) {

		Ticket ticket = form.converter();
		ticketRepository.save(ticket);

		URI uri = uriBuilder.path("/tickets/{id}").buildAndExpand(ticket.getId()).toUri();
		return ResponseEntity.created(uri).body(ticket.imprimeTicketCliente());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/aberto")
	public List<Ticket> pageAbertos() {

		List<Ticket> tickets = ticketRepository.findByStatus(StatusTicket.ABERTO);
		int tamanho = tickets.size();

		tickets = tm.estruturaTickets(tickets);
		
		List retorno = new ArrayList();
		
		if(tamanho == 0) {
			retorno.add("Não existem tickets abertos");
			return retorno;
		}
		
		retorno.add(String.format("Existem %d tickets abertos", tamanho));
		retorno.add("-------------------------");
		retorno.add(tickets);

		return retorno;
	}
	
//	Retorna todos os tickets dado a placa de um veículo
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/{placa}")
	public List<Ticket> getPorPlaca(@PathVariable String placa) {

		List<Ticket> tickets = ticketRepository.findByCarroPlaca(placa);
		int tamanho = tickets.size();
		
		for (Ticket ticket : tickets) {
			ticket.setValor(ticket.calculaValorAtual());
		}

		tickets = tm.estruturaTickets(tickets);
		
		List retorno = new ArrayList();
		
		if(tamanho == 0) {
			retorno.add(String.format("Não existem tickets registrados para a placa %s", placa));
			return retorno;
		}
		
		retorno.add(String.format("Existem %d tickets registrados para a placa %s", tamanho,placa));
		retorno.add("---------------------------------------------------");
		retorno.add(tickets);

		return retorno;
	}

//	Retorna o ticket aberto dado a placa do carro
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/aberto/{placa}")
	public List<Ticket> pageTicketAbertoPorPlaca(@PathVariable String placa) {

		List<Ticket> abertos = ticketRepository.findByCarroPlacaAndStatus(placa, StatusTicket.ABERTO);
		
		int tamanho = abertos.size();
		
		abertos = tm.estruturaTickets(abertos);
		
		List retorno = new ArrayList();
		
		if(tamanho == 0) {
			retorno.add(String.format("Não existem tickets abertos registrados para a placa %s", placa));
			return retorno;
		}
		
		retorno.add(String.format("Existe(m) %d ticket(s) aberto(s) registrado(s) para a placa %s", tamanho,placa));
		retorno.add("-------------------------------------------------------------------");
		retorno.add(abertos);

		return retorno;
	}

//	Fecha e calcula o valor do ticket aberto do veículo.
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/fecha/{placa}")
	public List fechaPorPlaca(@PathVariable String placa) {
		
		List<Ticket> abertos = ticketRepository.findByCarroPlacaAndStatus(placa,StatusTicket.ABERTO);
		List retorno = new ArrayList();
				
		if (abertos.size() == 1) {

			Ticket ultimoTicket = abertos.get(abertos.size() - 1);
			ultimoTicket.fechaTicket();
			ticketRepository.save(ultimoTicket);
			
			retorno.add("O ticket foi fechado com sucesso: ");
			retorno.add("-------------TICKET--------------");
			retorno.add(ultimoTicket.imprimeTicket());
			retorno.add("-------------TICKET--------------");
			
			return retorno;
		} else if (abertos.size() > 1) {
			
			retorno.add("Existe mais de 1 ticket aberto, por favor fechar diretamente pelo Ticket ID.");
			abertos = tm.estruturaTickets(abertos);
			retorno.add(abertos);

			return retorno;
		}
		
		List lista = new ArrayList<>();
		lista.add("Não existem tickets abertos registrados nesta placa.");

		return lista;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/fecha/id/{id}")
	public List fechaPorPlaca(@PathVariable Long id) {

		Ticket ticket = new Ticket();
		List retorno = new ArrayList();

		try {
			ticket = ticketRepository.findById(id).get();
		} catch (NoSuchElementException e) {
			
			List erro = new ArrayList();
			erro.add("Erro na operação:");
			erro.add("Não foi encontrado ticket com este id");
			return erro;
		}
		
		if(ticket.getStatus()==StatusTicket.FECHADO) {
			retorno.add("Erro na operação:");
			retorno.add("O ticket ja está fechado");
			retorno.add("-------------TICKET--------------");
			retorno.add(ticket.imprimeTicket());
			retorno.add("-------------TICKET--------------");
			
			return retorno;
		}
		
		ticket.fechaTicket();
		ticketRepository.save(ticket);
				
		retorno.add("O ticket foi fechado com sucesso: ");
		retorno.add("-------------TICKET--------------");
		retorno.add(ticket.imprimeTicket());
		retorno.add("-------------TICKET--------------");
		
		return retorno;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/populaDB")
	public List criaTicket() {
		
		List<Ticket> tickets = tm.criaTickets();
		List ticketsEstruturados = new ArrayList<>();
		
		ticketsEstruturados.add("Para fins de testes foram adicionados os seguintes tickets no banco de dados: ");
		
		for (Ticket ticket : tickets) {
			ticketRepository.save(ticket);
			ticketsEstruturados.add(ticket.imprimeTicket());
		}
		
		return ticketsEstruturados;
	}
}
