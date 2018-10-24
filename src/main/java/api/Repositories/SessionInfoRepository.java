/*package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Pojos.SessionInfo;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface SessionInfoRepository extends CrudRepository<SessionInfo, Integer> {
	
	//Check if username exist
	User findByName(String name);


	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);

	//Get username and sessionid by username.
	@Query(value = "SELECT name, sessionId FROM USER WHERE name = ?1", nativeQuery = true)
  	User findSessionByName(String name);

  	@Query("select u.name, u.sessionId from User u where u.name = ?1")
	SessionInfo findSessionByName(String name);
}
*/