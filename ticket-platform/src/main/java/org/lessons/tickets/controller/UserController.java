package org.lessons.tickets.controller;

import java.util.ArrayList;
import java.util.List;

import org.lessons.tickets.model.User;
import org.lessons.tickets.repository.TicketRepository;
import org.lessons.tickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping
	public String index(Model model) {
		
		List<User> userList = new ArrayList<User> ();
		
		userList = userRepository.findAll(Sort.by(Sort.Direction.ASC, "surname"));
		
		model.addAttribute("list", userList);
		
		return "/users/index";
	}

	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") Integer ticketId, Model model) {
		
		model.addAttribute("list", ticketRepository.findAll());
		
		model.addAttribute("user", userRepository.getReferenceById(ticketId));
		
		return "users/detail";
	}
}
