package api.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import api.Models.*;
import api.Pojos.*;

import java.util.List;
import java.time.*;

// This will be AUTO IMPLEMENTED by Spring into a Bean called personRepository
// CRUD refers Create, Read, Update, Delete

public interface PersonRepository extends CrudRepository<Person, Integer> {

	@Query("SELECT p FROM Person p WHERE person_id = :id AND status = \'active\'")
	Person getById(@Param("id") Integer id);

	@Query("SELECT p FROM Person p WHERE LOWER(p.first_name) = LOWER(:first_name) AND p.status = \'active\'")
	List<Person> findByFirstName(@Param("first_name") String first_name);

	@Query("SELECT p FROM Person p WHERE LOWER(p.last_name) = LOWER(:last_name) AND p.status = \'active\'")
	List<Person> findByLastName(@Param("last_name") String last_name);

	@Query("SELECT p FROM Person p WHERE LOWER(p.last_name) = LOWER(:last_name) AND LOWER(p.first_name) = LOWER(:first_name) AND LOWER(p.date_of_birth) = LOWER(:date_of_birth) AND p.status = \'active\'")
	Person findByFirstAndLastandBirth(@Param("first_name") String first_name, @Param("last_name") String last_name,
			@Param("date_of_birth") LocalDate date_of_birth);

	@Query("SELECT p FROM Person p WHERE p.status = \'active\'")
	List<Person> getAllActive();

	@Query("SELECT p FROM Person p WHERE address_id = :address_id AND status = \'active\'")
	List<Person> getByAddressId(@Param("address_id") Integer address_id);

}