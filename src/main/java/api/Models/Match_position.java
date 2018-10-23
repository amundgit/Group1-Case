package api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Match_position{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

	private String status = "active";
}

//SQL:
/*
CREATE TABLE MATCH_POSITION
(
  position VARCHAR(64),
  player_id INTEGER NOT NULL,
  match_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  FOREIGN KEY (player_id) REFERENCES PLAYER(player_id),
  FOREIGN KEY (match_id) REFERENCES "MATCH"(match_id)
);
*/