package org.lessons.tickets.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.lessons.tickets.model.Category;
import org.lessons.tickets.model.Role;
import org.lessons.tickets.model.Ticket;
import org.lessons.tickets.model.User;
import org.lessons.tickets.repository.CategoryRepository;
import org.lessons.tickets.repository.NoteRepository;
import org.lessons.tickets.repository.RoleRepository;
import org.lessons.tickets.repository.TicketRepository;
import org.lessons.tickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
	@Autowired
	private RoleRepository roleRepository;
	
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

		model.addAttribute("userList", userRepository.searchAvailableUsers());
		
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
		
		User userLogged = findUserLogged();
		
		model.addAttribute("user", userLogged);
		
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

	@GetMapping("/edit/{id}")
	public String editPage(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("ticket", ticketRepository.getReferenceById(id));
		
		model.addAttribute("userList", userRepository.searchAvailableUsers());
		
		model.addAttribute("categoryList", categoryRepository.findAll());
		
		User userLogged = findUserLogged();
		
		model.addAttribute("user", userLogged);
		
		return "/tickets/edit";
	}
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("ticket") Ticket ticket,
			BindingResult bindingResult, Model model) {

		User userLogged = findUserLogged();
		
		model.addAttribute("user", userLogged);
		
//		Object i = 1;
//		
//		Boolean userRole = userLogged.getRoles().contains(i);		
//		String type;
//		
//		if(userRole) {
//			type ="text";
//			} else {
//				type ="hidden";
//			}
//		 model.addAttribute("type", type);

		
		if(bindingResult.hasErrors()) {
			
			return "/tickets/edit";
		}
	
		ticketRepository.save(ticket);
		
		return "redirect:/main";
		
	}
	
	
	//metodi di servizio
	
	public User findUserLogged() {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName();
		
		Optional<User> user = userRepository.findByUsername(username);
		
		User userLogged = user.get();
		
		return userLogged;
	}
}
