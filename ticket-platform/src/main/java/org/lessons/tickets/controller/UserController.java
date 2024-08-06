package org.lessons.tickets.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.lessons.tickets.model.Ticket;
import org.lessons.tickets.model.User;
import org.lessons.tickets.repository.RoleRepository;
import org.lessons.tickets.repository.TicketRepository;
import org.lessons.tickets.repository.UserRepository;
import org.lessons.tickets.repository.UserStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserStateRepository userStateRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping
	public String index(Model model) {
		
		List<User> userList = new ArrayList<User> ();
		
		userList = userRepository.findAll(Sort.by(Sort.Direction.ASC, "surname"));
		
		model.addAttribute("list", userList);
		
		return "/users/index";
		
	}
	
	@GetMapping("/home")
	public String home(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
		String username = authentication.getName();

        Optional<User> user = userRepository.findByUsername(username);
        
        User userLogged = user.get();

        model.addAttribute("user", userLogged);
		
		return "/users/home";
	}

	@GetMapping("/show/{id}")
	public String show(@PathVariable("id") Integer ticketId, Model model,
				User user) {
		
		model.addAttribute("list", ticketRepository.findByUser(user));
		
		model.addAttribute("user", userRepository.getReferenceById(ticketId));
		
		model.addAttribute("stateList", userStateRepository.findAll());
		
		model.addAttribute("roleList", roleRepository.findAll());
		
		return "users/detail";
		
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@ModelAttribute("user") @PathVariable("id") Integer id, Model model,
			User user) {
		
		model.addAttribute("list", ticketRepository.findByUser(user));
		
		user = userRepository.findById(id).get();
		
		model.addAttribute("user", user);
	 	
	    return "/user/detail";

	    
	}
		
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("user") @PathVariable("id")Integer id,
			User user, BindingResult bindingResult, Model model) {
		
		List<Ticket> userTicketList = user.getTickets();
		
		model.addAttribute("list", userTicketList);
		
		model.addAttribute("stateList", userStateRepository.findAll());
		
		model.addAttribute("roleList", roleRepository.findAll());
		

			
			if(bindingResult.hasErrors()) {
				
				return "/users/detail";
			}
			
			userRepository.save(user);
			
			if(verifyRole()) {
				
				return "redirect:/user";

			}
			
			return "redirect:/user/show/{id}";
		
	}
	
	@GetMapping("/create")
	public String createPage(Model model) {
		
		model.addAttribute("user", new User());
		
		model.addAttribute("stateList", userStateRepository.findAll());
		
		model.addAttribute("roleList", roleRepository.findAll());
		
		return "/users/create";
	}
	
	@PostMapping("create")
	public String create(@Valid @ModelAttribute("user") User user, 
			BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			
			return "users/create";
		}
		
		userRepository.save(user);
		
		return "redirect:/user";
		
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		
		userRepository.deleteById(id);
		
		return "redirect:/user";
	}
	
	
	//metodi di servizio
	
	public User findUserLogged() {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = authentication.getName();
		
		Optional<User> user = userRepository.findByUsername(username);
		
		User userLogged = user.get();
		
		return userLogged;
	}
	
	public boolean verifyRole() {
		
		User userLogged =findUserLogged();
		
		Object i = 1;
		
		Boolean userRole = userLogged.getRoles().contains(i);
		
		return userRole;
	}
}
