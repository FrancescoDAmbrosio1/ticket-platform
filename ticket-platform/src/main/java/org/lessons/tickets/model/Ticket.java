package org.lessons.tickets.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ticket")
public class Ticket {
	
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "La scelta dello stato è obbligatoria")
	@Column(name = "stato", nullable = false)
	private String ticketState;
	
	@NotBlank(message = "L'inserimento del titolo è obbligatorio")
	@Column(name = "titolo", nullable = false)
	private String ticketTitle;
	
	@NotNull(message = "La scelta dell'operatore è obbligatoria")
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@NotNull(message = "La scelta della categoria è obbligatoria")
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
//	@ManyToOne
//	@JoinColumn(name = "note_id")
//	private Note note;

	@OneToMany(mappedBy = "ticket")
	public List<Note> notes;
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTicketState() {
		return ticketState;
	}

	public void setTicketState(String ticketState) {
		this.ticketState = ticketState;
	}

	public String getTicketTitle() {
		return ticketTitle;
	}

	public void setTicketTitle(String ticketTitle) {
		this.ticketTitle = ticketTitle;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
