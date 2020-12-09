package com.alura.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.estacionamento.estrutura.Estacionamento;

public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Long>{
}
