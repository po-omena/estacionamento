package com.alura.estacionamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.estacionamento.estrutura.Ticket;
import com.alura.estacionamento.estrutura.interna.StatusTicket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	List<Ticket> findByCarroPlaca(String placa);

	List<Ticket> findByStatus(StatusTicket status);
}
