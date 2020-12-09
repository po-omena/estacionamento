package com.alura.estacionamento.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.alura.estacionamento.estrutura.Carro;
import com.alura.estacionamento.estrutura.Cliente;
import com.alura.estacionamento.estrutura.Ticket;

public class TicketDto {
	
		private Long id;
		private Cliente cliente;
		private Carro carro;
		private LocalDateTime horarioEntrada;
		
		public TicketDto(Ticket ticket) {
			this.id = ticket.getId();
			this.carro = ticket.getCarro();
			this.cliente = ticket.getCliente();
			this.horarioEntrada = ticket.getHorarioEntrada();
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Cliente getCliente() {
			return cliente;
		}


		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}


		public Carro getCarro() {
			return carro;
		}


		public void setCarro(Carro carro) {
			this.carro = carro;
		}


		public LocalDateTime getHorarioEntrada() {
			return horarioEntrada;
		}


		public void setHorarioEntrada(LocalDateTime horarioEntrada) {
			this.horarioEntrada = horarioEntrada;
		}


		public static List<TicketDto> converter(List<Ticket> ticket) {
			return ticket.stream().map(TicketDto::new).collect(Collectors.toList());
		}

}

