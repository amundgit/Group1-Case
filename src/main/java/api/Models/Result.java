package api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Result{
	//Primary key needs some setup mebbeh? Lager basic for tests
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

	private String status = "active";
}

//SQL:
/*
CREATE TABLE RESULT
(
  score VARCHAR(4) NOT NULL,
  result VARCHAR(4) NOT NULL,
  match_id INTEGER NOT NULL,
  team_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (match_id),
  FOREIGN KEY (match_id) REFERENCES "MATCH"(match_id),
  FOREIGN KEY (team_id) REFERENCES TEAM(team_id)
);
*/