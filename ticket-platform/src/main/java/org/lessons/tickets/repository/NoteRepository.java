package org.lessons.tickets.repository;

import java.util.List;

import org.lessons.tickets.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepository extends JpaRepository<Note, Integer> {
	
	public List<Note> findAllById( Integer ticketId);
	

}
