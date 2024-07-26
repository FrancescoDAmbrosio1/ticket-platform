package org.lessons.tickets.repository;

import org.lessons.tickets.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
