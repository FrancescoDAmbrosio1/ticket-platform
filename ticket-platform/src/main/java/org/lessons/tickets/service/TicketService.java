package org.lessons.tickets.service;

import java.util.List;
import java.util.Optional;

import org.lessons.tickets.model.Ticket;

public interface TicketService {
	

	public Optional<Ticket> findById(Integer id);
	
	public List<Ticket> findAll();
	
	public List<Ticket> findByTicketState(String ticketState);
	
	public List<Ticket> findByCategoryId(Integer category);


}
