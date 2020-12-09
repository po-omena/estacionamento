package com.alura.estacionamento.estrutura;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alura.estacionamento.estrutura.interna.StatusTicket;
import com.sun.istack.NotNull;

@Entity
@Table(name = "Ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@NotNull
	@ManyToOne(cascade = {CascadeType.ALL})
	private Carro carro;
	private BigDecimal valor;
	@NotNull
	private LocalDateTime horarioEntrada = LocalDateTime.now();
	private LocalDateTime horarioSaida;
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusTicket status = StatusTicket.ABERTO;

	public Ticket() {
	}

	public Ticket(Ticket ticket) {
		this.carro = ticket.getCarro();
		this.valor = BigDecimal.valueOf(0);
	}
	
	public Ticket(Carro carro) {
		super();
		this.carro = carro;
		this.valor = BigDecimal.valueOf(0);
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getHorarioEntrada() {
		return horarioEntrada;
	}

	public void setHorarioEntrada(LocalDateTime horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}

	public LocalDateTime getHorarioSaida() {
		return horarioSaida;
	}

	public void setHorarioSaida(LocalDateTime horarioSaida) {
		this.horarioSaida = horarioSaida;
	}

	public StatusTicket getStatus() {
		return status;
	}

	public void setStatus(StatusTicket status) {
		this.status = status;
	}

	public BigDecimal checaValor() {
		return BigDecimal.valueOf(1);
	}
}
