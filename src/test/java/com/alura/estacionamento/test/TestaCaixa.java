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
class TestaCaixa {

	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	TicketManager tm = new TicketManager();
	
	@Test
	public void testGetCaixa() {
		
		//Só é testado o getCaixa geral pois se o testBusca específico não falha, o get caixa não irá falhar dado que
		//que o get específico usa a busca.
		
		List<Ticket> tickets = tm.criaTickets(); //lista criada inicialmente
		List<Ticket> ticketsSalvos = new ArrayList<>(); //lista preenchida pela tentativa de persist
		List<Ticket> fromDb = new ArrayList<>(); //Lista preenchida pelos valores do DB
		
		double valorTotalLocal = 0.0;
		
		valorTotalLocal = tm.getCaixa(tickets);
		
		for (Ticket ticket : tickets) {
			ticketsSalvos.add(em.persist(ticket));
		}
		
		fromDb = ticketRepository.findAll();
		double valorFromDb = 0.0;
		
		valorFromDb = tm.getCaixa(fromDb);
		
		Assert.assertEquals(ticketsSalvos, fromDb);
		Assert.assertTrue(valorTotalLocal==valorFromDb);
		
		System.out.println(valorTotalLocal);
		System.out.println(valorFromDb);
	}
}
