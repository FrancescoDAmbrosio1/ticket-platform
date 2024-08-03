package org.lessons.tickets.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "note")
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "creazione", nullable = false)
	private LocalDate creationNoteDate;
	
	@NotBlank(message = "Il testo della nota è obbligatorio")
	@Column(name = "testo", nullable = false, columnDefinition="text")
	private String noteText;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "ticket_id")
	private Ticket ticket;
	
//	@OneToMany(mappedBy = "note")
//	public List<Ticket> tickets;

	public Integer getId() {
		return id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getCreationNoteDate() {
		return creationNoteDate;
	}

	public void setCreationNoteDate(LocalDate creationNoteDate) {
		this.creationNoteDate = creationNoteDate;
	}

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
