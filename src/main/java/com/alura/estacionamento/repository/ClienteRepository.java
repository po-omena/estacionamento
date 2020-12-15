package com.alura.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.estacionamento.estrutura.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
}
