package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Team;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TeamRepository extends CrudRepository<Team, String> {
	
	//Team findById(String name); //test

	//@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	//User verifyUser(@Param("name")String name, @Param("password")String password);
}