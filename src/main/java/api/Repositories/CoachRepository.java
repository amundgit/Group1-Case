package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Coach;

import java.util.List;


public interface CoachRepository extends CrudRepository<Coach, Integer> {
	/*Kinda mal?
	User findByName(String name); //test

	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);*/
	@Query("SELECT c FROM Coach c WHERE person_id = :person_id AND status = \'active\'")
	Coach getByPersonId(@Param("person_id")Integer person_id);

	@Query("SELECT c FROM Coach c WHERE coach_id = :coach_id AND status = \'active\'")
	Coach getById(@Param("coach_id")Integer coach_id);

	@Query("SELECT c FROM Coach c WHERE status = \'active\'")
	List<Coach> getAllActive();
}

/*
@Query("SELECT a FROM Address a WHERE status = \'active\'")
	List<Address> getAllActive();
*/