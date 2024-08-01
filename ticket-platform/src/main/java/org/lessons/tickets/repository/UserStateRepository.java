package org.lessons.tickets.repository;

import org.lessons.tickets.model.UserState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStateRepository extends JpaRepository<UserState, Integer> {

}
