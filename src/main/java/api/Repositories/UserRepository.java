package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.User;
import api.Pojos.SessionInfo;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
	
	//Check if username exist
	User findByName(String name);


	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);

	//Get username and sessionid by username.
  	@Query("SELECT new api.Pojos.SessionInfo(u.name, u.sessionId) FROM User u WHERE u.name = ?1")
	SessionInfo findSessionByName(String name);
}