package api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Match_goal{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer goal_id;

	private String status = "active";
}

//SQL:
/*
CREATE TABLE MATCH_GOAL
(
  goal_id INTEGER NOT NULL,
  description VARCHAR(64),
  goal_type_id INTEGER NOT NULL,
  match_id INTEGER NOT NULL,
  player_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (goal_id),
  FOREIGN KEY (goal_type_id) REFERENCES GOAL_TYPE(goal_type_id),
  FOREIGN KEY (match_id) REFERENCES "MATCH"(match_id),
  FOREIGN KEY (player_id) REFERENCES PLAYER(player_id)
);
*/