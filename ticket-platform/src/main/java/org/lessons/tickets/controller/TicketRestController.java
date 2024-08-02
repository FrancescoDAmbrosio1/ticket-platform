package org.lessons.tickets.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.tickets.model.Ticket;
import org.lessons.tickets.response.Payload;
import org.lessons.tickets.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TicketRestController {
	
	@Autowired
	private TicketService ticketService;

	
	
	@GetMapping("/ticket/{id}")
	public ResponseEntity<Payload<Ticket>> get(@PathVariable("id") Integer id){
	
		Optional<Ticket> ticket = ticketService.findById(id);
		
		if (ticket.isPresent()) {
			return ResponseEntity.ok(new Payload<Ticket>(ticket.get(), null, HttpStatus.OK));
		}else {
			return ResponseEntity.ok(new Payload<Ticket>(null, "Ticket con id " + id + " non trovato", HttpStatus.NOT_FOUND));
		}
		
	}
	
	@GetMapping("/ticket")
	public ResponseEntity findAllTickets() {
		
		List<Ticket> tickets = ticketService.findAll();
		
		
		if (!tickets.isEmpty()) {
			
			return new ResponseEntity<>(tickets, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Non sono presenti ticket ", HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@GetMapping("/ticket/state/{ticketState}")
	public ResponseEntity  findByStatus(@PathVariable("ticketState") String ticketState){
		
		List<Ticket> ticket = ticketService.findByTicketState(ticketState);
		
		if (!ticket.isEmpty()) {
			return new ResponseEntity<>(ticket, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Nessun Ticket con lo stato: " + ticketState + " trovato.", HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
	@GetMapping("/ticket/category/{category}")
	public ResponseEntity findByCategory(@PathVariable("category") Integer category){
		
		List<Ticket> ticket = ticketService.findByCategoryId(category);
		
		if (!ticket.isEmpty()) {
			return new ResponseEntity<>(ticket, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Non ci sono Ticket per la categoria: " + category, HttpStatus.NOT_FOUND);
		}
		
		
	}
	
	
}
