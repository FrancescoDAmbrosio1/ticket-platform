package org.lessons.tickets.repository;

import java.util.List;

import org.lessons.tickets.model.Ticket;
import org.lessons.tickets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<Ticket, Integer> { 
//	Query custom
	@Query("SELECT t FROM Ticket t WHERE t.ticketTitle LIKE '%'||:input||'%' ")
			
    public List<Ticket> search( String input);
	
	public List<Ticket> findByUser(User user);
	

}
