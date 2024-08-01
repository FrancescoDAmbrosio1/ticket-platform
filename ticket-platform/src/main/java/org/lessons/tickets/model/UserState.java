package org.lessons.tickets.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "userPersonalState")
public class UserState {
	
	@Id
	private Integer id;
	
	@Column(name = "stato_personale", nullable = false)
	private boolean personalState;
	
	@OneToMany(mappedBy = "userState")
	public List<User> users;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isPersonalState() {
		return personalState;
	}

	public void setPersonalState(boolean personalState) {
		this.personalState = personalState;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
