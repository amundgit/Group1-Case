package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Address;
import api.Pojos.IdInfo;

import java.util.List;


public interface AddressRepository extends CrudRepository<Address, Integer> {
	/*
	 * Kinda mal? User findByName(String name); //test
	 */

	@Query("SELECT a FROM Address a WHERE address_id = :id AND status = \'active\'")
	Address getById(@Param("id") Integer id);

	@Query("SELECT a FROM Address a WHERE LOWER(address_line_1) = LOWER(:address) AND status = \'active\'")
	Address getByAddress(@Param("address") String address);

	@Query("SELECT address_id FROM Address a WHERE LOWER(address_line_1) = LOWER(:address_line_1) AND status = \'active\'")
	Integer getIdByAddress(@Param("address_line_1") String address_line_1);

	@Query("SELECT a FROM Address a WHERE LOWER(address_line_1) = LOWER(:address_line_1) AND LOWER(address_line_2) = LOWER(:address_line_2) AND LOWER(address_line_3) = LOWER(:address_line_3) AND LOWER(postal_code) = LOWER(:postal_code) AND LOWER(city) = LOWER(:city) AND LOWER(country) = LOWER(:country) AND status = \'active\'")
	Address getByCompleteAddress(@Param("address_line_1") String address_line_1, @Param("address_line_2") String address_line_2, @Param("address_line_3") String address_line_3,
		@Param("postal_code") String postal_code, @Param("city") String city, @Param("country") String country);

	@Query("SELECT a FROM Address a WHERE status = \'active\'")
	List<Address> getAllActive();

}

/*
@Query("SELECT g FROM Goal_type g WHERE status = \'active\'")
	List<Goal_type> getAllActive();
*/