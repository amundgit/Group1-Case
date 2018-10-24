package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Association;

import java.util.List;


public interface AssociationRepository extends CrudRepository<Association, Integer> {
	/*Kinda mal?
	User findByName(String name); //test

	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);*/

	@Query("SELECT a FROM Association a WHERE association_id = :id")
	Association getById(@Param("id")Integer id);
}