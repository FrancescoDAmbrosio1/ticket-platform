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
	public String edit(@PathVariable("id") Integer id, Model model) {
		
		model.addAttribute("list", ticketRepository.findAll());
		
		model.addAttribute("user",  userRepository.getReferenceById(id));
		
		
		return "/users/detail";
	}
	
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("user") User user,
			BindingResult bindingResult, Model model) {
		
//		String identifierNumber = user.getIdentifierNumber();
//		
//		Optional<User> userOpt = userRepository.findByidentifierNumber(identifierNumber);
//		
//		if(userOpt.isPresent()) {
//			
//			User usingUser = userOpt.get();
//			
//			List<Ticket> openTicket = ticketRepository.findByUser(usingUser);
//			
//			if (usingUser.getUserState().isPersonalState() == false && openTicket.size() > 0) {
//				model.addAttribute("errorMessage",
//						"Impossibile impostare lo stato su Non Disponibile, ci sono ticket aperti o in corso.");
//				model.addAttribute("user", usingUser);
//				
//				return "/show/{id}";
//			}
//			
//			usingUser.setSurname(user.getSurname());
//            usingUser.setName(user.getName());
//            usingUser.setIdentifierNumber(user.getIdentifierNumber());
//            usingUser.setMail(user.getMail());
//            usingUser.setPassword(user.getPassword());
//            usingUser.setUrlImgProfile(user.getUrlImgProfile());
//            usingUser.setUserState(user.getUserState());
//            usingUser.setRoles(user.getRoles());
//            
			
			if(bindingResult.hasErrors()) {
				
				return "/users/detail";
			}
			
			userRepository.save(user);
//		}
	
	
		
		return "redirect:/user";
		
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
		
}