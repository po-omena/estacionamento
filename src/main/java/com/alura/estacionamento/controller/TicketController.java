package com.alura.estacionamento.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.alura.estacionamento.estrutura.interna.StatusTicket;
import com.alura.estacionamento.controller.form.TicketForm;
import com.alura.estacionamento.estrutura.Ticket;
import com.alura.estacionamento.repository.TicketRepository;

@RequestMapping(value = "/ticket")
@RestController
public class TicketController {

	@Autowired
	private TicketRepository ticketRepository;

	@GetMapping
	public List<Ticket> lista(String placa) {
		if (placa == null) {
			List<Ticket> tickets = ticketRepository.findAll();
			return tickets;
		} else {
			List<Ticket> tickets = ticketRepository.findByCarroPlaca(placa);
			return tickets;
		}

	}
	
	@GetMapping("/aberto")
	public List<Ticket> lista() {
		List<Ticket> tickets = ticketRepository.findAll();
		List<Ticket> abertos = new ArrayList<Ticket>();
		
		StatusTicket abertoEnum = StatusTicket.ABERTO;
		for (Ticket ticket : tickets) {
			if(ticket.getStatus().equals(abertoEnum)){
				abertos.add(ticket);
			}
		}
		return abertos;
	}


	@PostMapping
	public ResponseEntity<Ticket> criaTicket(@RequestBody TicketForm form, UriComponentsBuilder uriBuilder) {
		Ticket ticket = form.converter();
		ticketRepository.save(ticket);

		URI uri = uriBuilder.path("/tickets/{id}").buildAndExpand(ticket.getId()).toUri();
		return ResponseEntity.created(uri).body(new Ticket(ticket));
	}

	@GetMapping("/{placa}")
	public List<Ticket> detalhar(@PathVariable String placa) {

		List<Ticket> tickets = ticketRepository.findByCarroPlaca(placa);
		tickets.get(0).fechaTicket();
		System.out.println(tickets.get(0).getTimeDiff());
		ticketRepository.save(tickets.get(0));

		return tickets;
	}
}
