package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Owner;

import java.util.List;


public interface OwnerRepository extends CrudRepository<Owner, Integer> {
	/*Kinda mal?
	User findByName(String name); //test

	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);*/
	@Query("SELECT o FROM Owner o WHERE person_id = :person_id AND status = \'active\'")
	Owner getByPersonId(@Param("person_id")Integer person_id);

	@Query("SELECT o FROM Owner o WHERE owner_id = :owner_id AND status = \'active\'")
	Owner getById(@Param("owner_id")Integer owner_id);
}