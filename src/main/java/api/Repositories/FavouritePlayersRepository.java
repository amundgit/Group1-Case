package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.FavouritePlayers;

import java.util.List;


public interface FavouritePlayersRepository extends CrudRepository<FavouritePlayers, Integer> {
	/*Kinda mal?
	User findByName(String name); //test

	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);*/
	//@Query("SELECT p FROM Player p WHERE person_id = :person_id AND status = \'active\'")
	//Player getByPersonId(@Param("person_id")Integer person_id);

	@Query("SELECT f FROM FavouritePlayers f WHERE favourite_id = :favourite_id AND status = \'active\'")
	FavouritePlayers getById(@Param("favourite_id")Integer favourite_id);

	@Query("SELECT f FROM FavouritePlayers f WHERE user_id = :user_id AND player_id = :player_id AND status = \'active\'")
	FavouritePlayers getByUserAndPlayer(@Param("user_id")Integer user_id, @Param("player_id")Integer player_id);

	@Query("SELECT f FROM FavouritePlayers f WHERE user_id = :user_id AND status = \'active\'")
	List<FavouritePlayers> getAllByUser(@Param("user_id")Integer user_id);
}