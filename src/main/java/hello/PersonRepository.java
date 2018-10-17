package hello;

import org.springframework.data.repository.CrudRepository;

import hello.Person;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called personRepository
// CRUD refers Create, Read, Update, Delete

public interface PersonRepository extends CrudRepository<Person, Integer> {
	//List<Person> findBy_First_name(String first_name);
}