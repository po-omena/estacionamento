package com.alura.estacionamento.controller.form;

import com.alura.estacionamento.estrutura.Carro;
import com.alura.estacionamento.estrutura.Cliente;
import com.alura.estacionamento.estrutura.Ticket;

public class TicketForm {

	private Carro carro;
	private Cliente cliente;

	public TicketForm() {
	}

	public TicketForm(Carro carro, Cliente cliente) {
		super();
		this.carro = carro;
		this.cliente = cliente;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Ticket converter() {
		return new Ticket(carro);
	}

}



