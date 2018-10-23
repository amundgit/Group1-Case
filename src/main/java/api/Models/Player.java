package api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Player{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer player_id;

	private String status = "active";
}

//SQL:
/*
CREATE TABLE PLAYER
(
  player_id INTEGER NOT NULL,
  normal_position VARCHAR(64),
  number VARCHAR(8),
  person_id INTEGER NOT NULL,
  team_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (player_id),
  FOREIGN KEY (person_id) REFERENCES PERSON(person_id),
  FOREIGN KEY (team_id) REFERENCES TEAM(team_id)
);
*/