package com.alura.estacionamento.estrutura;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.alura.estacionamento.estrutura.interna.Modelo;
import com.sun.istack.NotNull;

@Entity
@Table(name = "Carro")
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@NotNull
	private String placa;
	private String marca;
	@OneToOne(cascade = CascadeType.ALL)
	private Modelo modelo;
	@ManyToOne(cascade = CascadeType.ALL)
	private Cliente cliente;

	public Carro() {
	}

	public Carro(String marca, String placa, Cliente cliente, Modelo modelo) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.cliente = cliente;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

}
