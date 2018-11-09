package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Location;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface LocationRepository extends CrudRepository<Location, Integer> {
    /*
     * Kinda mal? User findByName(String name); //test
     * 
     * @Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(:name) AND LOWER(u.password) = LOWER(:password)"
     * ) User verifyUser(@Param("name")String name, @Param("password")String
     * password);
     */

    @Query("SELECT a FROM Location a WHERE LOWER(name) = LOWER(:name) AND status = \'active\'")
    Location getByName(@Param("name") String name);

    @Query("SELECT a FROM Location a WHERE location_id = :location_id AND status = \'active\'")
    Location getById(@Param("location_id") Integer location_id);

    @Query("SELECT a FROM Location a WHERE address_id = :address_id AND status = \'active\'")
    List<Location> getByAddressId(@Param("address_id") Integer address_id);

    @Query("SELECT a FROM Location a WHERE status = \'active\'")
    List<Location> getAllActive();
}