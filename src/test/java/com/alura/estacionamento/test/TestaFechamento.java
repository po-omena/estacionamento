package com.alura.estacionamento.test;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.alura.estacionamento.estrutura.Carro;
import com.alura.estacionamento.estrutura.Cliente;
import com.alura.estacionamento.estrutura.Ticket;
import com.alura.estacionamento.estrutura.interna.Endereco;
import com.alura.estacionamento.estrutura.interna.Modelo;
import com.alura.estacionamento.estrutura.interna.StatusTicket;
import com.alura.estacionamento.estrutura.interna.versoes;
import com.alura.estacionamento.repository.TicketRepository;

@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)

class TestaFechamento {

	@Autowired
	private TestEntityManager em;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Test
	public void testFechaticket() {
		
		Endereco endereco1 = new Endereco("SANTA MONICA","38412-333","UBERLANDIA","CASA","456","RUA TESTE Nº1");
		Cliente cliente1 = new Cliente("77824753093","ROGERIO SANTOS","(44) 67076-4726", endereco1);
		Modelo modelo1 = new Modelo("GOL", 2014,versoes.HATCH);
		Carro carro1 = new Carro("MARCA 1","XKJ-9482",cliente1,modelo1);
		Ticket ticket = new Ticket(LocalDateTime.parse("2020-10-09T13:15:39"),LocalDateTime.parse("2020-10-09T14:15:39"),StatusTicket.FECHADO,5.0,carro1);
		
		Ticket savedTicket = em.persist(ticket);
		Ticket fromDB = ticketRepository.getOne(savedTicket.getId());
		System.out.println(ticket.imprimeTicket());
		Assert.assertEquals(fromDB, savedTicket);
		
		ticket.fechaTicket();
		savedTicket = em.persist(ticket);
		fromDB = ticketRepository.getOne(savedTicket.getId());

		
		Assert.assertEquals(fromDB, savedTicket);
	}
}
