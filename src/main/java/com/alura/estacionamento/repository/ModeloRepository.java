package com.alura.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.estacionamento.estrutura.interna.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Long>{

}
