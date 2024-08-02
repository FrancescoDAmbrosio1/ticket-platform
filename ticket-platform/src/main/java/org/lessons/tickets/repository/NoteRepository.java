package org.lessons.tickets.repository;

import java.util.List;

import org.lessons.tickets.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface NoteRepository extends JpaRepository<Note, Integer> {
	
//	// Query custom
//	@Query("SELECT * FROM note n WHERE n.ticket_id LIKE :id", nativeQuery = true)
//			
//	public List<Note> findByTicket(Integer id);
	
	 @Query(value = "SELECT n.* FROM note n WHERE n.ticket_id = :i", nativeQuery = true)
	    List<Note> findAllNoteByTicket(@Param("i") Integer i);

}
