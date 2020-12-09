package com.alura.estacionamento.estrutura.interna;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Modelo")
public class Modelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private String modeloNome;
	private int modeloAno;
	@Enumerated(EnumType.STRING)
	private versoes modeloVersao;

	public String getModeloNome() {
		return modeloNome;
	}

	public void setModeloNome(String modeloNome) {
		this.modeloNome = modeloNome;
	}

	public int getModeloAno() {
		return modeloAno;
	}

	public void setModeloAno(int modeloAno) {
		this.modeloAno = modeloAno;
	}

	public versoes getModeloVersao() {
		return modeloVersao;
	}

	public void setModeloVersao(versoes modeloVersao) {
		this.modeloVersao = modeloVersao;
	}

}
