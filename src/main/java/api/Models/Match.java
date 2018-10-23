package api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Match{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer match_id;

	private String status = "active";
}

//SQL:
/*
CREATE TABLE MATCH
(
  match_id INTEGER NOT NULL,
  match_date DATE NOT NULL,
  season_id INTEGER NOT NULL,
  location_id INTEGER NOT NULL,
  home_team_id INTEGER NOT NULL,
  away_team_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (match_id),
  FOREIGN KEY (season_id) REFERENCES SEASON(season_id),
  FOREIGN KEY (location_id) REFERENCES LOCATION(location_id),
  FOREIGN KEY (home_team_id) REFERENCES TEAM(team_id),
  FOREIGN KEY (away_team_id) REFERENCES TEAM(team_id)
);
*/