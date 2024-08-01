package org.lessons.tickets.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "L'inserimento del nome è obbligaotorio")
	@Column(name = "nome", nullable = false)
	private String name;
	
	@NotBlank(message = "L'inserimento del cognome è obbligaotorio")
	@Column(name = "cognome", nullable = false)
	private String surname;

	@NotBlank(message = "L'inserimento della matricola è obbligaotorio")
	@Column(name = "matricola", nullable = false, unique = true)
	private String identifierNumber;
	
	@NotBlank(message = "L'inserimento della mail è obbligaotorio")
	@Column(name = "email", nullable = false, unique = true)
	private String mail;
	
	@NotBlank(message = "L'inserimento della Password è obbligaotorio")
	@Column(name = "password", nullable = false)
	private String password;
//	
//	@Column(name = "stato-disponibile", nullable = false)
//	private boolean personalState;
	
	@NotBlank(message = "L'inserimento dell'Url è obbligaotorio")
	@Column(name = "Url img profilo", nullable = false)
	private String urlImgProfile;
	
//	@NotNull(message = "L'inserimento dello stato è obbligatorio")
	@ManyToOne
	@JoinColumn(name = "userState_id")
	private UserState userState;
	
//	@NotNull(message = "L'inserimento del Ruolo è obbligatorio")
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


	public UserState getUserState() {
		return userState;
	}

	public void setUserState(UserState userState) {
		this.userState = userState;
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
