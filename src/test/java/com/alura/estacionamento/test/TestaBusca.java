package com.alura.estacionamento.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.alura.estacionamento.controller.manager.TicketManager;
import com.alura.estacionamento.estrutura.Ticket;
import com.alura.estacionamento.repository.TicketRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)

class TestaBusca {

	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	TicketManager tm = new TicketManager();
	
	
	@Test
	public void testBuscaPorPlaca() {
		
		List<Ticket> tickets = buscaTotal();
		
// Teste de busca por Placa		
		//Método não leva em consideração se existem 2 ou mais tickets sob a mesma placa,
		//dado que só queremos testar se a busca por placa funciona
		
		List<Ticket> ticketJavaPorPlaca = new ArrayList<Ticket>();
		
		for (Ticket ticket : tickets) {
			if(ticket.getCarro().getPlaca()=="ZSU-3758") {
				ticketJavaPorPlaca.add(ticket);
			}
		}
		
		List<Ticket> ticketsDbPorPlaca = ticketRepository.findByCarroPlaca("ZSU-3758");
		
		Assert.assertEquals(ticketJavaPorPlaca, ticketsDbPorPlaca);
		
	}
	


	public List<Ticket> buscaTotal(){
		
		List<Ticket> tickets = tm.criaTickets();
		List<Ticket> savedTickets = new ArrayList<>(); 
		
		for (int i = 0; i < tickets.size(); i++) {
			savedTickets.add(em.persist(tickets.get(i)));
		}
		
		List<Ticket> listFromDb = ticketRepository.findAll();
		
		Assert.assertEquals(savedTickets, listFromDb);
		Assert.assertEquals(tickets, listFromDb);
		
		return tickets;		
	}
}