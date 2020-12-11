package com.alura.estacionamento.controller.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.alura.estacionamento.estrutura.Ticket;
import com.alura.estacionamento.estrutura.interna.StatusTicket;
import com.alura.estacionamento.repository.TicketRepository;

public class TicketManager {
	
	@SuppressWarnings("rawtypes")
	public List getAbertos(List<Ticket> tickets, TicketRepository tr) {

		if (tickets.isEmpty()) {
			tickets = tr.findAll();
		}

		List<Ticket> abertos = new ArrayList<Ticket>();

		for (Ticket ticket : tickets) {
			if (ticket.getStatus().equals(StatusTicket.ABERTO)) {

				ticket.setValor(ticket.calculaValorAtual());
				abertos.add(ticket);
			}
		}
		return abertos;
	}
	@SuppressWarnings("rawtypes")
	public List getFechados(List<Ticket> tickets, TicketRepository tr) {
		
		if (tickets == null) {
			tickets = tr.findAll();
		}
		
		List<Ticket> fechados = new ArrayList<Ticket>();
		
		for (Ticket ticket : tickets) {
			if (ticket.getStatus().equals(StatusTicket.FECHADO)) {
				fechados.add(ticket);
			}
		}
		return fechados;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })

	public List estruturaTickets(List<Ticket> tickets) {

		List ticketsEstruturados = new ArrayList<>();
		for (Ticket ticket : tickets) {
			ticketsEstruturados.add(ticket.imprimeTicket());
		}
		return ticketsEstruturados;
	}

	public Double getCaixa(List<Ticket> tickets) {
		Double caixaTotal = 0.0;

		for (Ticket ticket : tickets) {
			ticket.setValor(ticket.calculaValorAtual());
			caixaTotal += ticket.getValor();
		}
		return caixaTotal;
	}

	public List<Ticket> getTicketsAno(@PathVariable int ano, TicketRepository tr) {

		List<Ticket> tickets = tr.findAll();
		List<Ticket> ticketPorAno = new ArrayList<Ticket>();

		for (Ticket ticket : tickets) {
			if (ticket.getHorarioEntrada().getYear() == ano) {
				ticketPorAno.add(ticket);
			}
		}

		return ticketPorAno;
	}

	public List<Ticket> getTicketsMes(int ano, int mes, TicketRepository tr) {

		List<Ticket> tickets = getTicketsAno(ano, tr);
		List<Ticket> ticketPorMes = new ArrayList<Ticket>();

		for (Ticket ticket : tickets) {
			if (ticket.getHorarioEntrada().getMonthValue() == mes) {
				ticketPorMes.add(ticket);
			}
		}

		return ticketPorMes;
	}

	@SuppressWarnings("rawtypes")
	public List getTicketsDia(int ano, int mes, int dia, TicketRepository tr) {

		List<Ticket> tickets = getTicketsMes(ano, mes, tr);
		List<Ticket> ticketsPorDia = new ArrayList<Ticket>();

		for (Ticket ticket : tickets) {
			if (ticket.getHorarioEntrada().getDayOfMonth() == dia) {
				ticketsPorDia.add(ticket);
			}
		}
		return ticketsPorDia;
	}

}
