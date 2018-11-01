package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Match_goal;

import java.util.List;


public interface Match_goalRepository extends CrudRepository<Match_goal, Integer> {
	/*Kinda mal?
	User findByName(String name); //test

	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);*/
	@Query("SELECT g FROM Match_goal g WHERE goal_id = :goal_id AND status = \'active\'")
	Match_goal getById(@Param("goal_id")Integer goal_id);
}