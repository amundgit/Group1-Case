package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Result;
import api.CompositeIds.ResultId;

import java.util.List;


public interface ResultRepository extends CrudRepository<Result, ResultId> {
	/*Kinda mal?
	User findByName(String name); //test

	@Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)")
	User verifyUser(@Param("name")String name, @Param("password")String password);*/
	@Query("SELECT r FROM Result r WHERE result_id = :result_id AND status = \'active\'")
	Result getById(@Param("result_id")ResultId result_id);
}