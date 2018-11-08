package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Match;

import java.util.List;


public interface MatchRepository extends CrudRepository<Match, Integer> {
	/*Kinda mal?
	User findByName(String name); //test

	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);*/
	@Query("SELECT m FROM Match m WHERE match_id = :match_id AND status = \'active\'")
	Match getById(@Param("match_id")Integer match_id);

	@Query("SELECT m FROM Match m WHERE status = \'active\'")
	List<Match> getAllActive();
}