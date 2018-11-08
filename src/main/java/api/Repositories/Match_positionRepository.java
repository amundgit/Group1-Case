package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Match_position;
import api.CompositeIds.Match_positionId;

import java.util.List;


public interface Match_positionRepository extends CrudRepository<Match_position, Match_positionId> {
	/*Kinda mal?
	User findByName(String name); //test

	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);*/
	@Query("SELECT m FROM Match_position m WHERE position_id = :position_id AND status = \'active\'")
	Match_position getById(@Param("position_id")Match_positionId position_id);

	@Query("SELECT m FROM Match_position m WHERE status = \'active\'")
	List<Match_position> getAllActive();
}