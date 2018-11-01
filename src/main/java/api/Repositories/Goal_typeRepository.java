package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Goal_type;

import java.util.List;


public interface Goal_typeRepository extends CrudRepository<Goal_type, Integer> {
	/*Kinda mal?
	User findByName(String name); //test

	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);*/
	@Query("SELECT g FROM Goal_type g WHERE type = :type AND status = \'active\'")
	Goal_type getByType(@Param("type")String type);
}