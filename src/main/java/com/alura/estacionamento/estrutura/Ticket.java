package com.alura.estacionamento.estrutura;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import com.alura.estacionamento.estrutura.interna.StatusTicket;
import com.sun.istack.NotNull;

@Entity
@Table(name = "Ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotNull
	@ManyToOne(cascade = { CascadeType.MERGE })
	private Carro carro;

	@NotNull
	private LocalDateTime horarioEntrada = LocalDateTime.now();
	private LocalDateTime horarioSaida;

	@Formula("TIMEDIFF(NOW(),horario_entrada)")
	private Timestamp timeDiff;

	private Double valor;

	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusTicket status = StatusTicket.ABERTO;

	public Ticket() {
	}

	public Ticket(Ticket ticket) {
		this.carro = ticket.getCarro();
		this.valor = 0.0;
	}

	public Ticket(Carro carro) {
		super();
		this.carro = carro;
		this.valor = 0.0;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Timestamp getTimeDiff() {
		return timeDiff;
	}

	public void setTimeDiff(Timestamp timeDiff) {
		this.timeDiff = timeDiff;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
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

	/*
	 * public BigDecimal calculaValor() {
	 * 
	 * return BigDecimal.valueOf(1); }
	 */
	public int calculaValorAtual() {

		@SuppressWarnings("deprecation")
		int horaParcial = timeDiff.getMinutes();
		@SuppressWarnings("deprecation")
		int horas = timeDiff.getHours();

		if (horaParcial > 0) {
			horas += 1;
		}

		return horas;
	}

	
	public double fechaTicket() {
		
		setHorarioSaida(LocalDateTime.now());
		
		Instant entrada = this.horarioEntrada.atZone(ZoneId.of("UTC-3")).toInstant();
		Instant saida = this.horarioSaida.atZone(ZoneId.of("UTC-3")).toInstant();

		Duration timeElapsed = Duration.between(entrada, saida);

		double horaParcial = timeElapsed.toMinutes();
		double horas = timeElapsed.toHours();
		horaParcial = horaParcial % 60; // Hora Parcial

		if (horaParcial > 0) {
			horas += 1;
		}
		
		this.setStatus(StatusTicket.FECHADO);
		this.setValor(5.0+(2.0*horas));
		
		System.out.println("O valor final é de: " + valor);
		return horas;
	}

}
