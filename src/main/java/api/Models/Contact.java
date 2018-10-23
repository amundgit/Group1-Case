package api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Contact{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer contact_id;

	private String status = "active";
}

//SQL:
/*
CREATE TABLE CONTACT
(
  contact_id INTEGER NOT NULL,
  contact_type VARCHAR(64) NOT NULL,
  contact_detail VARCHAR(64) NOT NULL,
  person_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (contact_id),
  FOREIGN KEY (person_id) REFERENCES PERSON(person_id)
);
*/