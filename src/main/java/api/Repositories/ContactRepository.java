package api.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import api.Models.Contact;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called personRepository
// CRUD refers Create, Read, Update, Delete

public interface ContactRepository extends CrudRepository<Contact, Integer> {

    @Query("SELECT c FROM Contact c WHERE c.person.person_id = :person_id AND status = \'active\'")
    List<Contact> findByID(@Param("person_id") Integer person_id);

    @Query("SELECT c FROM Contact c WHERE (c.person.person_id) = (:person_id) AND LOWER(contact_type) = \'work\' AND status = \'active\'")
    List<Contact> findByIDandWork(@Param("person_id") Integer person_id);

    @Query("SELECT c FROM Contact c WHERE (c.person.person_id) = (:person_id) AND LOWER(contact_type) = \'home\' AND status = \'active\'")
    List<Contact> findByIDandHome(@Param("person_id") Integer person_id);

    @Query("SELECT c FROM Contact c WHERE (c.person.person_id) = (:person_id) AND LOWER(contact_detail) = LOWER(:contact_detail) AND status = \'active\'")
    Contact findByIDandDetails(@Param("person_id") Integer person_id, @Param("contact_detail") String contact_detail);

    @Query("SELECT c FROM Contact c WHERE c.contact_id = :contact_id AND status = \'active\'")
    Contact findByContactId(@Param("contact_id") Integer contact_id);

    @Query("SELECT c FROM Contact c WHERE status = \'active\'")
    List<Contact> getAllActive();

}