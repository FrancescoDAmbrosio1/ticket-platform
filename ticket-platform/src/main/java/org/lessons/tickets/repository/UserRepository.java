package org.lessons.tickets.repository;


import java.util.List;
import java.util.Optional;

import org.lessons.tickets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	
//	Query custom
	@Query("SELECT u FROM User u WHERE u.userState.personalState = true")
			
    public List<User> searchAvailableUsers();

	Optional<User> findByidentifierNumber(String identifierNumber);
	
	Optional<User> findByUsername(String username);

}
