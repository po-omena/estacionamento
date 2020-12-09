package com.alura.estacionamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.estacionamento.repository.CarroRepository;
import com.alura.estacionamento.repository.ClienteRepository;
import com.alura.estacionamento.repository.EstacionamentoRepository;
import com.alura.estacionamento.repository.TicketRepository;

@RestController
@RequestMapping("/topicos")
public class TicketController {
	
	@SuppressWarnings("unused")
	@Autowired
	private CarroRepository carroRepository;
	
	@SuppressWarnings("unused")
	@Autowired
	private ClienteRepository clienteRepository;
	
	@SuppressWarnings("unused")
	@Autowired
	private EstacionamentoRepository estacionamentoRepository;
	
	@SuppressWarnings("unused")
	@Autowired
	private TicketRepository ticketRepository;

}
