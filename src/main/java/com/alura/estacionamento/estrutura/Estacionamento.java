package com.alura.estacionamento.estrutura;



import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.alura.estacionamento.estrutura.interna.Endereco;
import com.sun.istack.NotNull;

@Entity
@Table(name = "Estacionamento")
public class Estacionamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@NotNull
	@OneToOne
	private Endereco endereco;
	private BigDecimal caixa;

	public Long getEstacionamentoId() {
		return Id;
	}

	public void setEstacionamentoId(Long estacionamentoId) {
		this.Id = estacionamentoId;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public BigDecimal getCaixa() {
		return caixa;
	}

	public void setCaixa(BigDecimal caixa) {
		this.caixa = caixa;
	}

}
