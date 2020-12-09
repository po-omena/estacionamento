package com.alura.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.estacionamento.estrutura.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{
}
