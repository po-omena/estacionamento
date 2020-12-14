package com.alura.estacionamento.estrutura;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
	@ManyToOne(cascade = CascadeType.ALL)
	private Carro carro;

	@NotNull
	private LocalDateTime horarioEntrada = LocalDateTime.now();
	private LocalDateTime horarioSaida;

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

	public Ticket(LocalDateTime horarioEntrada, LocalDateTime horarioSaida, 
				  StatusTicket status, Double valor, Carro carro) {
		super();
		this.carro = carro;
		this.horarioEntrada = horarioEntrada;
		this.horarioSaida = horarioSaida;
		this.valor = valor;
		this.status = status;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

//	public String getTimeDiff() {
//		return timeDiff;
//	}
//
//	public void setTimeDiff(String timeDiff) {
//		this.timeDiff = timeDiff;
//	}

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

	public double calculaValorAtual() {
		
		if(this.status==StatusTicket.FECHADO) {
			return this.valor;
		}
		
		Duration timeElapsed = getTimeDiff();

		double horaParcial = timeElapsed.toMinutes();
		double horas = timeElapsed.toHours();
		horaParcial = horaParcial % 60; // Hora Parcial

		if (horaParcial > 0) {
			horas += 1;
		}
		
		if(horas==0) {
			return 5;
		}
		
		Double valor = (5.0+(2.0*(horas-1)));
		
		return valor;
	}

	private Duration getTimeDiff() {
		
		Instant entrada = this.horarioEntrada.atZone(ZoneId.of("UTC-3")).toInstant();
		Instant saida = null;
		
		if(this.horarioSaida==null) {
			saida = Instant.now();
		} else {
			saida = this.horarioSaida.atZone(ZoneId.of("UTC-3")).toInstant();
		}

		Duration timeElapsed = Duration.between(entrada, saida);
		return timeElapsed;
	}

	
	public double fechaTicket() {
		
		if(status == StatusTicket.FECHADO) {
			System.out.println("O ticket está fechado.");
			System.out.println(String.format("Valor Final: R$%.2f",this.getValor()));
			return this.getValor();
		}
		
		setHorarioSaida(LocalDateTime.now());
		setStatus(StatusTicket.FECHADO);
		this.setValor(this.calculaValorAtual());
		
		return this.getValor();
	}
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List imprimeTicket() {
		
		List lista = new ArrayList();
		lista.add(String.format("Ticket ID: %d", this.getId()));
		lista.add(String.format("Status: %s", getStatus()));
		
		lista.add(String.format("Placa: %s",this.getCarro().getPlaca()));
		lista.add(String.format("Marca: %s",this.getCarro().getMarca()));
		lista.add(String.format("Ano: %s",this.getCarro().getModelo().getModeloAno()));
		lista.add(String.format("Modelo: %s",(this.getCarro().getModelo().getModeloNome())));
		lista.add(String.format("Tipo: %s",this.getCarro().getModelo().getModeloVersao()));
		
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
		
		lista.add(String.format("Entrada: %s", this.getHorarioEntrada().truncatedTo(ChronoUnit.SECONDS).format(dtf)));
		
		if(this.getHorarioSaida()!=null) {
			lista.add(String.format("Saída: %s", this.getHorarioSaida().truncatedTo(ChronoUnit.SECONDS).format(dtf)));
		}else {
			lista.add("Saída: NÃO REGISTRADA");
		}
		
		lista.add(String.format("Tempo de Permanencia: %dH %dM",this.getTimeDiff().toHours(),this.getTimeDiff().minusHours(this.getTimeDiff().toHours()).toMinutes()));
				
		lista.add(String.format("Valor: %s", this.getValor()));
		
	return lista;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List imprimeTicketCliente() {
		
		List lista = new ArrayList();
		
		lista.add("O valor para a primeira hora é: R$ 5,00");		
		lista.add("O valor para as demais hora é: R$ 2,00");
		lista.add("----------------------------------------");
		
		lista.add(String.format("Ticket ID: %d", this.getId()));
		lista.add(String.format("Status: %s", getStatus()));
		
		lista.add(String.format("Placa: %s",this.getCarro().getPlaca()));
		lista.add(String.format("Marca: %s",this.getCarro().getMarca()));
		lista.add(String.format("Ano: %s",this.getCarro().getModelo().getModeloAno()));
		lista.add(String.format("Modelo: %s",(this.getCarro().getModelo().getModeloNome())));
		lista.add(String.format("Tipo: %s",this.getCarro().getModelo().getModeloVersao()));
		
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
		
		lista.add(String.format("Entrada: %s", this.getHorarioEntrada().truncatedTo(ChronoUnit.SECONDS).format(dtf)));
		lista.add("----------------------------------------");
		lista.add("Tenha um bom dia!");	
		
		return lista;
	}
}
