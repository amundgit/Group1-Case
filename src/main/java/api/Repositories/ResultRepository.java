package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Result;

import java.util.List;


public interface ResultRepository extends CrudRepository<Result, Integer> {
	/*Kinda mal?
	User findByName(String name); //test

	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);*/
	//@Query("SELECT r FROM Result r WHERE result_id.match_id = :match_id AND status = \'active\'")
	//Result getByMatchId(@Param("match_id")Integer match_id);
}