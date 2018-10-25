package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.User;
import api.Pojos.*;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
	
	//Check if username exist
	User findByName(String name);


	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);

	//Get userid, username and sessionid by username.
  	@Query("select new api.Pojos.UserPojo(u.id, u.name, u.sessionId) from User u where u.name = ?1")
	UserPojo findSessionByName(String name);

	//Get userid, username and sessionid by username.
  	@Query("select new api.Pojos.UserPojo(u.role) from User u where u.id = ?1")
	UserPojo findRoleByUserid(String userid);
}