package org.lessons.tickets.controller;

import java.util.ArrayList;
import java.util.List;

import org.lessons.tickets.model.Category;
import org.lessons.tickets.model.Ticket;
import org.lessons.tickets.model.User;
import org.lessons.tickets.repository.CategoryRepository;
import org.lessons.tickets.repository.NoteRepository;
import org.lessons.tickets.repository.TicketRepository;
import org.lessons.tickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
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
		
		model.addAttribute("category", new Category());

		model.addAttribute("user", new User());

		model.addAttribute("userList", userRepository.findAll(Sort.by(Sort.Direction.ASC, "name")));
		
		model.addAttribute("categoryList", categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "categoryName")));
		
		
		
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
	public String show(@PathVariable("id") Integer ticketId, Model model) {
		
		model.addAttribute("list", noteRepository.findAll(Sort.by(Sort.Direction.DESC,"creationNoteDate")));
		
		model.addAttribute("ticket", ticketRepository.getReferenceById(ticketId));
		
		return "tickets/show";
	}
	
	@GetMapping("/search")
	public String search(@Param("input") String input, Model model) {

		List<Ticket> list = new ArrayList<Ticket>();
		
		if(!input.isEmpty()) {
			
			list = ticketRepository.search(input);
			
		} 
			
		model.addAttribute("list", list);	
		
		return "tickets/index";
		
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
				
		
		ticketRepository.deleteById(id);
		
		return "redirect:/main";
	}

}
