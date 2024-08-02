package org.lessons.tickets.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.lessons.tickets.model.Role;
import org.lessons.tickets.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DatabaseUserDetails implements UserDetails{

	private final Integer id;
	private final String email;
	private final String password;
	private final Set<GrantedAuthority> authorities;
	
	public DatabaseUserDetails(User user) {
		this.id = user.getId();
		this.email = user.getMail();
		this.password = user.getPassword();
		this.authorities = new HashSet<GrantedAuthority>();
		for(Role role : user.getRoles()) {
			this.authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
