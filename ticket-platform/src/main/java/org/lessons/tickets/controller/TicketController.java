package org.lessons.tickets.controller;

import java.util.ArrayList;
import java.util.List;

import org.lessons.tickets.model.Ticket;
import org.lessons.tickets.model.User;
import org.lessons.tickets.repository.TicketRepository;
import org.lessons.tickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/main")
public class TicketController {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public String ticketDashboard(Model model) {
		
		List<Ticket> ticketsList = new ArrayList<Ticket> ();
		
		ticketsList = ticketRepository.findAll();
		
		model.addAttribute("list", ticketsList);
		
		return "/tickets/index";
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		
		model.addAttribute("ticket", new Ticket());
		
		model.addAttribute("userList", userRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
		
		model.addAttribute("user", new User());
		
		return "/tickets/create";
	}
	
	@PostMapping("create")
	public String store(@Valid @ModelAttribute("ticket") Ticket ticket, 
			BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			
			return "tickets/create";
		}
		
		ticketRepository.save(ticket);
		
		return "redirect:/main";
	}
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") Integer pizzaId, Model model) {
		
		model.addAttribute("ticket", ticketRepository.getReferenceById(pizzaId));
		
		return "tickets/show";
	}

}
