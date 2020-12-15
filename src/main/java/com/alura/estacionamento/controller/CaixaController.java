package com.alura.estacionamento.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.estacionamento.controller.manager.TicketManager;
import com.alura.estacionamento.estrutura.Ticket;
import com.alura.estacionamento.estrutura.interna.StatusTicket;
import com.alura.estacionamento.repository.TicketRepository;

@Transactional
@RequestMapping(value = "/caixa")
@RestController
public class CaixaController {

	@Autowired
	private TicketRepository ticketRepository;
	
	TicketManager tm = new TicketManager();
	
	@GetMapping
	public String getCaixaTotal() {

		List<Ticket> tickets = ticketRepository.findAll();
		Double caixaTotal = tm.getCaixa(tickets);

		String retorno = String.format("O caixa total do estacionamento é: %.2f", caixaTotal);
		return retorno;
	}
	
	@GetMapping("/{ano}")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List caixaAno(@PathVariable int ano) {
		
		List<Ticket>ticketsAno = tm.getTicketsAno(ano, ticketRepository);
		
		Double caixa = tm.getCaixa(ticketsAno);
		int tamanho = ticketsAno.size();
		ticketsAno = tm.estruturaTickets(ticketsAno);
		
		List retorno = new ArrayList();
		
		retorno.add(String.format("O caixa para o ano de %d é: R$%.2f.", ano, caixa));
		retorno.add(String.format("Existe(m) %d ticket(s) no periodo:", tamanho));
		retorno.add(ticketsAno);
		
		return retorno;
	}
	
	
	@GetMapping("/{ano}/{mes}")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List caixaMes(@PathVariable int ano, @PathVariable int mes) {
		
		List<Ticket>tickets = tm.getTicketsMes(ano, mes, ticketRepository);
		
		Double caixa = tm.getCaixa(tickets);
		int tamanho = tickets.size();
		tickets = tm.estruturaTickets(tickets);
		
		List retorno = new ArrayList();
		
		retorno.add(String.format("O caixa para o periodo de %d-%d é: R$%.2f.", ano, mes, caixa));
		retorno.add(String.format("Existe(m) %d ticket(s) no periodo:", tamanho));
		retorno.add(tickets);
		
		return retorno;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/{ano}/{mes}/{dia}")
	public List caixaDia(@PathVariable int ano, @PathVariable int mes, @PathVariable int dia) {
		
		List<Ticket> tickets = tm.getTicketsDia(ano, mes, dia, ticketRepository);

		Double caixa = tm.getCaixa(tickets);
		int tamanho = tickets.size();
		tickets = tm.estruturaTickets(tickets);
		
		List retorno = new ArrayList();
		
		retorno.add(String.format("O caixa para o periodo de %d-%d-%s é: R$%.2f.", dia, mes, ano, caixa));
		retorno.add(String.format("Existe(m) %d ticket(s) no periodo:", tamanho));
		retorno.add(tickets);
		
		return retorno;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping("/veiculo/{placa}")
	public List caixaPlaca(@PathVariable String placa) {
		
		List<Ticket> tickets = ticketRepository.findByCarroPlacaAndStatus(placa, StatusTicket.FECHADO);
		
		Double caixa = tm.getCaixa(tickets);
		int tamanho = tickets.size();
		tickets = tm.estruturaTickets(tickets);
		
		List retorno = new ArrayList();
		
		retorno.add(String.format("O veículo de placa %s tem registrado: R$%.2f em tickets.", placa, caixa));
		retorno.add(String.format("Existe(m) %d ticket(s) *fechados* registrados para este veículo:", tamanho));
		retorno.add(tickets);
		
		return retorno;
	}
	
}
