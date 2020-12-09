package com.alura.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.estacionamento.estrutura.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{
}
