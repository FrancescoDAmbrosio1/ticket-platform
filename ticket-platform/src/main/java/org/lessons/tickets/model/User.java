package org.lessons.tickets.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome", nullable = false)
	private String name;
	
	@Column(name = "cognome", nullable = false)
	private String surname;

	@Column(name = "matricola", nullable = false, unique = true)
	private String identifierNumber;
	
	@Column(name = "email", nullable = false, unique = true)
	private String mail;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "stato-disponibile", nullable = false)
	private boolean personalState;
	
	@Column(name = "Url img profilo", nullable = false)
	private String urlImgProfile;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles;

	@OneToMany(mappedBy = "user")
	public List<Ticket> tickets;
	
	@OneToMany(mappedBy = "user")
	public List<Note> notes;
	
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public Integer getId() {
		return id;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getIdentifierNumber() {
		return identifierNumber;
	}

	public void setIdentifierNumber(String identifierNumber) {
		this.identifierNumber = identifierNumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPersonalState() {
		return personalState;
	}

	public void setPersonalState(boolean personalState) {
		this.personalState = personalState;
	}

	public String getUrlImgProfile() {
		return urlImgProfile;
	}

	public void setUrlImgProfile(String urlImgProfile) {
		this.urlImgProfile = urlImgProfile;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
