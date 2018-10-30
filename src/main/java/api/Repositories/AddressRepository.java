package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Address;
import api.Pojos.IdInfo;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AddressRepository extends CrudRepository<Address, Integer> {
	/*
	 * Kinda mal? User findByName(String name); //test
	 * 
	 * @Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)"
	 * ) User verifyUser(@Param("name")String name, @Param("password")String
	 * password);
	 */

	@Query("SELECT a FROM Address a WHERE address_id = :id")
	Address getById(@Param("id") Integer id);

	@Query("SELECT a FROM Address a WHERE LOWER(address_line_1) = LOWER(:address)")
	Address getByAddress(@Param("address") String address);

	@Query("SELECT addres_id FROM Address a WHERE LOWER(address_line_1) = LOWER(:address_line_1)")
	Integer getIdByAddress(@Param("address_line_1") String address_line_1);

}