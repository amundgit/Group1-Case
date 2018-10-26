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

	// Check if username exist
	User findByName(String name);

	@Query("INSERT INTO User (session_id) SELECT (session_id) FROM User u WHERE u.name = :name ")
	User setUserSession(@Param("session_id") String session_id, @Param("name") String name);

	// Get userid, username and sessionid by username.
	@Query("select new api.Pojos.SessionInfo(u.id, u.name, u.sessionId) from User u where u.name = ?1")
	SessionInfo findSessionByName(String name);

	// Get userrole by userid.
	@Query("select new api.Pojos.UserRoleInfo(u.role) from User u where u.id = ?1")
	UserRoleInfo findRoleByUserid(int userid);
}