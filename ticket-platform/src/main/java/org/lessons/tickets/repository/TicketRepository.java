package org.lessons.tickets.repository;

import java.util.List;

import org.lessons.tickets.model.Ticket;
import org.lessons.tickets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket, Integer> { 
//	Query custom
	@Query("SELECT t FROM Ticket t WHERE t.ticketTitle LIKE '%'||:input||'%' ")
			
    public List<Ticket> search( String input);
	
	public List<Ticket> findByUser(User user);

	public List<Ticket> findByTicketState(String ticketState);
	
	@Query("SELECT t FROM Ticket t WHERE t.category.id = :category")
    List<Ticket> findByCategoryId(@Param("category") Integer category);
	

}
