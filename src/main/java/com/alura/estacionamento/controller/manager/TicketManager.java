package com.alura.estacionamento.controller.manager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.alura.estacionamento.estrutura.Carro;
import com.alura.estacionamento.estrutura.Cliente;
import com.alura.estacionamento.estrutura.Ticket;
import com.alura.estacionamento.estrutura.interna.Endereco;
import com.alura.estacionamento.estrutura.interna.Modelo;
import com.alura.estacionamento.estrutura.interna.StatusTicket;
import com.alura.estacionamento.estrutura.interna.versoes;
import com.alura.estacionamento.repository.TicketRepository;

public class TicketManager {
	
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
	
	public List<Ticket> criaTickets(){
		Modelo modelo1 = new Modelo("GOL", 2014,versoes.HATCH);
		Modelo modelo2 = new Modelo("PICASSO",2011,versoes.SUV);
		Modelo modelo3 = new Modelo("JEEP RENEGADE",2014,versoes.SUV);
		Modelo modelo4 = new Modelo("CAMARO GT", 2018, versoes.LUXO);
		Modelo modelo5 = new Modelo("SAVEIRO", 1997, versoes.PICAPE);
		
		Endereco endereco1 = new Endereco("SANTA MONICA","38412-333","UBERLANDIA","CASA","456","RUA TESTE Nº1");
		Endereco endereco2 = new Endereco("JARDIM ALTAMIRA","47412-533","ARAGUARI","AP 302","1034","RUA DAS PALMEIRAS");
		Endereco endereco3 = new Endereco("BAIRRO ALTO","98312-003","UBERLANDIA","CASA","3842","RUA RIACHUELO");
		Endereco endereco4 = new Endereco("QUADRA 110 SUL","88135-543","UBERLANDIA","CASA","97","ALAMEDA 101");
		Endereco endereco5 = new Endereco("IRACEMA","54874-093","GOIANIA","CASA","980","RUA SANTO MARINHO");

		Cliente cliente1 = new Cliente("77824753093","ROGERIO SANTOS","(44) 67076-4726", endereco1);
		Cliente cliente2 = new Cliente("67730510022","ISABELA PRADO","(22) 53740-2188",endereco2);
		Cliente cliente3 = new Cliente("77824753093","LUCAS ALEIXO","(11) 42005-1767",endereco3);
		Cliente cliente4 = new Cliente("46785709008","GABRIEL PEREIRA","(83) 41202-8337",endereco4);
		Cliente cliente5 = new Cliente("14939223000","FLORENCA GOULART","(58) 79759-2610",endereco5);
		
		Carro carro1 = new Carro("MARCA 1","XKJ-9482",cliente1,modelo1);
		Carro carro2 = new Carro("MARCA 2","ZSU-3758",cliente2,modelo2);
		Carro carro3 = new Carro("MARCA 3","GMT-1964",cliente3,modelo3);
		Carro carro4 = new Carro("MARCA 4","FPQ-4821",cliente4,modelo4);
		Carro carro5 = new Carro("MARCA 5","NIV-3456",cliente5,modelo5);
		
		Ticket ticket1 = new Ticket(LocalDateTime.parse("2020-10-09T13:15:39"),LocalDateTime.parse("2020-10-09T14:15:39"),StatusTicket.FECHADO,5.0,carro1);
		Ticket ticket2 = new Ticket(LocalDateTime.parse("2020-12-10T10:17:15"),null,StatusTicket.ABERTO,0.0,carro2);
		Ticket ticket3 = new Ticket(LocalDateTime.parse("2020-12-10T10:50:14"),null,StatusTicket.ABERTO,0.0,carro3);
		Ticket ticket4 = new Ticket(LocalDateTime.parse("2020-12-10T09:34:47"),null,StatusTicket.ABERTO,0.0,carro4);
		Ticket ticket5 = new Ticket(LocalDateTime.parse("2020-11-15T09:43:24"),LocalDateTime.parse("2020-11-15T10:25:35"),StatusTicket.FECHADO,10.0,carro1);
		Ticket ticket6 = new Ticket(LocalDateTime.parse("2020-12-09T07:48:16"),LocalDateTime.parse("2020-12-09T12:48:16"),StatusTicket.FECHADO,13.0,carro5);
		
		List<Ticket> tickets = new ArrayList<Ticket>();
		
		tickets.add(ticket1);
		tickets.add(ticket2);
		tickets.add(ticket3);
		tickets.add(ticket4);
		tickets.add(ticket5);
		tickets.add(ticket6);
		
		return tickets;
	}

}
