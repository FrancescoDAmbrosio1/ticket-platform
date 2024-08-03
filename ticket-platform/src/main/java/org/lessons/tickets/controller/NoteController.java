package org.lessons.tickets.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.lessons.tickets.model.Note;
import org.lessons.tickets.model.User;
import org.lessons.tickets.repository.NoteRepository;
import org.lessons.tickets.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/note")
public class NoteController {
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/create")
	public String creationPage( Model model){
		
		model.addAttribute("note", new Note());
		
		User userLogged = findUserLogged();
		
		model.addAttribute("user", userLogged);
		
		return "tickets/show";
	}
	
	
	@PostMapping("create")
	public String store(@Valid @ModelAttribute("note") Note note, 
			BindingResult bindingResult, Model model) {
		
		User userLogged = findUserLogged();
		
		model.addAttribute("user", userLogged);
		
		note.setUser(userLogged);
		
		note.setCreationNoteDate(LocalDate.now());
		
		if(bindingResult.hasErrors()) {
			
			return "tickets/show";
		}
		
		noteRepository.save(note);
		
		if(verifyRole()) {
			
			return "redirect:/main";

		}
		
		return "redirect:/user/home";
		
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
