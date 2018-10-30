package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Season;

import java.util.List;


public interface SeasonRepository extends CrudRepository<Season, Integer> {
	/*Kinda mal?
	User findByName(String name); //test

	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);*/
	@Query("SELECT s FROM Season s WHERE name = :name AND status = \'active\'")
	Season getByName(@Param("name") String name);

	@Query("SELECT s FROM Season s WHERE season_id = :season_id AND status = \'active\'")
	Season getById(@Param("season_id") Integer season_id);
}