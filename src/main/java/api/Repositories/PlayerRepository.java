package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.*;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player, Integer> {
	/*
	 * Kinda mal? User findByName(String name); //test
	 * 
	 * @Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)"
	 * ) User verifyUser(@Param("name")String name, @Param("password")String
	 * password);
	 */
	@Query("SELECT p FROM Player p WHERE person_id = :person_id AND status = \'active\'")
	Player getByPersonId(@Param("person_id") Integer person_id);

	@Query("SELECT p FROM Player p WHERE player_id = :player_id AND status = \'active\'")
	Player getById(@Param("player_id") Integer player_id);

	@Query("SELECT person FROM Player p WHERE team_id = :team_id AND status = \'active\'")
	List<Person> getByTeam(@Param("team_id") String team_id);

}