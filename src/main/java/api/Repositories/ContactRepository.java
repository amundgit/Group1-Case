package api.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import api.Models.Contact;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called personRepository
// CRUD refers Create, Read, Update, Delete

public interface ContactRepository extends CrudRepository<Contact, Integer> {

    @Query("SELECT c FROM Contact c WHERE c.person.person_id = :person_id")
    List<Contact> findByID(@Param("person_id") Integer person_id);

    @Query("SELECT c FROM Contact c WHERE (c.person.person_id) = (:person_id) AND LOWER(c.contact_type) = \'work\'")
    List<Contact> findByIDandWork(@Param("person_id") Integer person_id);

    @Query("SELECT c FROM Contact c WHERE (c.person.person_id) = (:person_id) AND LOWER(c.contact_type) = \'home\'")
    List<Contact> findByIDandHome(@Param("person_id") Integer person_id);

    @Query("SELECT c FROM Contact c WHERE (c.person.person_id) = (:person_id) AND LOWER(c.contact_type) = LOWER(:contact_type) and LOWER(c.contact_detail) and LOWER(:contact_detail)")
    Contact findByIDandTypeandDetails(@Param("person_id") Integer person_id, @Param("contact_type") String contact_type,
            @Param("contact_detail") String contact_detail);

}