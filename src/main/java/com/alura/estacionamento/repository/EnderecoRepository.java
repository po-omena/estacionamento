package com.alura.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.estacionamento.estrutura.interna.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

}
