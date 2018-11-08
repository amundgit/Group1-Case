package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.FavouriteTeams;

import java.util.List;


public interface FavouriteTeamsRepository extends CrudRepository<FavouriteTeams, Integer> {
	/*Kinda mal?
	User findByName(String name); //test

	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);*/
	//@Query("SELECT p FROM Player p WHERE person_id = :person_id AND status = \'active\'")
	//Player getByPersonId(@Param("person_id")Integer person_id);

	@Query("SELECT f FROM FavouriteTeams f WHERE favourite_id = :favourite_id AND status = \'active\'")
	FavouriteTeams getById(@Param("favourite_id")Integer favourite_id);

	@Query("SELECT f FROM FavouriteTeams f WHERE user_id = :user_id AND team_id = :team_id AND status = \'active\'")
	FavouriteTeams getByUserAndTeam(@Param("user_id")Integer user_id, @Param("team_id")String team_id);

	@Query("SELECT f FROM FavouriteTeams f WHERE user_id = :user_id AND favourite_id = :favourite_id AND status = \'active\'")
	FavouriteTeams getByUserAndId(@Param("user_id")Integer user_id, @Param("favourite_id")Integer favourite_id);

	@Query("SELECT f FROM FavouriteTeams f WHERE user_id = :user_id AND status = \'active\'")
	List<FavouriteTeams> getAllByUser(@Param("user_id")Integer user_id);

	@Query("SELECT f FROM FavouriteTeams f WHERE status = \'active\'")
	List<FavouriteTeams> getAllActive();
}