package org.lessons.tickets.repository;

import org.lessons.tickets.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoteRepository extends JpaRepository<Note, Integer> {
	

}
