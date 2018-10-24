package api.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import api.Models.Person;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called personRepository
// CRUD refers Create, Read, Update, Delete

public interface PersonRepository extends CrudRepository<Person, Integer> {

	@Query("SELECT p FROM Person p WHERE LOWER(first_name) = LOWER(:first_name)")
	List<Person> findByFirstName(@Param("first_name") String first_name);

	@Query("SELECT p FROM Person p WHERE LOWER(last_name) = LOWER(:last_name)")
	List<Person> findByLastName(@Param("last_name") String last_name);

	@Query("SELECT p FROM Person p WHERE LOWER(last_name) = LOWER(:last_name) AND LOWER(first_name) = LOWER(:first_name) AND LOWER(date_of_birth) = LOWER(:date_of_birth)")
	Person findByFirstAndLastandBirth(@Param("first_name") String first_name, @Param("last_name") String last_name,
			@Param("date_of_birth") String date_of_birth);

	@Query("SELECT p FROM Person p WHERE id = :person_id")
	Person getById(@Param("person_id") Integer person_id);
}