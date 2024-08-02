package org.lessons.tickets.service;

import java.util.List;
import java.util.Optional;

import org.lessons.tickets.model.Ticket;
import org.lessons.tickets.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImplements implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Override
	public Optional<Ticket> findById(Integer id) {
		
		return ticketRepository.findById(id);
	}

	@Override
	public List<Ticket> findAll() {
		
		return ticketRepository.findAll();
	}

	@Override
	public List<Ticket> findByTicketState(String ticketState) {
		
		return ticketRepository.findByTicketState(ticketState);
	}

	@Override
	public List<Ticket> findByCategoryId(Integer category) {
		
		return ticketRepository.findByCategoryId(category);
	}

}
