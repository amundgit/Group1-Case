package api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Team{
	@Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer team_id;

	private String status = "active";
}

//SQL:
/*
CREATE TABLE TEAM
(
  team_id VARCHAR(64) NOT NULL,
  owner_id INTEGER NOT NULL,
  association_id INTEGER NOT NULL,
  coach_id INTEGER NOT NULL,
  location_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (team_id),
  FOREIGN KEY (owner_id) REFERENCES OWNER(owner_id),
  FOREIGN KEY (association_id) REFERENCES ASSOCIATION(association_id),
  FOREIGN KEY (coach_id) REFERENCES COACH(coach_id),
  FOREIGN KEY (location_id) REFERENCES LOCATION(location_id)
);
*/