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

	@Query("SELECT u FROM User u WHERE u.name = :name AND u.status = \'active\'")
	User findByName(@Param("name") String name);

	@Query("SELECT id FROM User u WHERE name = :name AND status = \'active\'")
	Integer findIdByName(@Param("name") String name);

	@Query("SELECT u FROM User u WHERE id = :user_id AND status = \'active\'")
	User getById(@Param("user_id") Integer user_id);

	// Get userid, username and sessionid by username.
	@Query("select new api.Pojos.SessionInfo(u.id, u.name, u.sessionId) from User u where u.name = ?1 AND status = \'active\'")
	SessionInfo findSessionByName(String name);

	// Get userrole by userid.
	@Query("select new api.Pojos.UserRoleInfo(u.role) from User u where u.id = ?1  AND status = \'active\'")
	UserRoleInfo findRoleByUserid(int userid);

	// Get sessionid and role by username.
	@Query("select new api.Pojos.SessionVerifyInfo(u.sessionId, u.role) from User u where u.name = ?1 AND status = \'active\'")
	SessionVerifyInfo findSessionVerifyByUsername(String name);

	@Query("SELECT u FROM User u WHERE status = \'active\'")
	List<User> getAllActive();
}