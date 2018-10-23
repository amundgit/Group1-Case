package api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Owner{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer owner_id;

	private String status = "active";
}

//SQL:
/*
CREATE TABLE OWNER
(
  owner_id INTEGER NOT NULL,
  person_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (owner_id),
  FOREIGN KEY (person_id) REFERENCES PERSON(person_id)
);
*/