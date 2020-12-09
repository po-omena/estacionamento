package com.alura.estacionamento.estrutura;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	@ManyToOne
	private Carro carro;
	private BigDecimal valor;
	@NotNull
	private LocalDateTime horarioEntrada=LocalDateTime.now();
	private LocalDateTime horarioSaida;
	@NotNull
	@OneToOne
	private Estacionamento filial;
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusTicket status = StatusTicket.ABERTO;

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

	public Estacionamento getFilial() {
		return filial;
	}

	public void setFilial(Estacionamento filial) {
		this.filial = filial;
	}

	public StatusTicket getStatus() {
		return status;
	}

	public void setStatus(StatusTicket status) {
		this.status = status;
	}

}
