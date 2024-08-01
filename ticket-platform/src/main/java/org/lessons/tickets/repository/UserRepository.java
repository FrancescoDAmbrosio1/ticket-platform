package org.lessons.tickets.repository;


import org.lessons.tickets.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
	
////	Query custom
//	@Query("UPDATE User u SET u.personalState = '%'||:input||'%' WHERE u.id = '%'||:id||'%' ")
//			
//    public User update( boolean input, Integer id);
//	@Modifying
//	@Query("update User u set u.personalState = :state where u.id = :id")
//	void updateUserState(@Param(value = "id") Integer id, @Param(value = "state") boolean state);

}
