package api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Goal_type{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer goal_type_id;

	private String status = "active";
}

//SQL:
/*
CREATE TABLE GOAL_TYPE
(
  goal_type_id INTEGER NOT NULL,
  type VARCHAR(64) NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (goal_type_id)
);
*/