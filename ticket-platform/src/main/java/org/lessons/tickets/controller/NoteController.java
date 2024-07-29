package org.lessons.tickets.controller;

import org.lessons.tickets.model.Note;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NoteController {
	
	@GetMapping("/create")
	public String creationPage(Model model){
		
		model.addAttribute("note", new Note());
		
		return "/notes/create";
	}

}
