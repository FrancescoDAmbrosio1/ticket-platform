package org.lessons.tickets.service;

import java.util.Optional;

import org.lessons.tickets.model.User;
import org.lessons.tickets.repository.UserRepository;
import org.lessons.tickets.security.DatabaseUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByUsername(email);
		
		if(user.isPresent()) {
		
			return new DatabaseUserDetails(user.get());
		
		} else {
		
			throw new UsernameNotFoundException("Username not found");
		
		}
	
	}

}