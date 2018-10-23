package api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDate; //will this create sql date?

@Entity // This tells Hibernate to make a table out of this class
public class Season{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer season_id;

    private LocalDate start_date; //WILL NEED TESTING

    private LocalDate end_date; //WILL NEED TESTING

    private String name;

    private String description;

    private String status = "active";

    //SQL:
	/*
	CREATE TABLE SEASON
	(
	  season_id INTEGER NOT NULL,
	  start_date DATE NOT NULL,
	  end_date DATE NOT NULL,
	  name VARCHAR(64) NOT NULL,
	  description VARCHAR(64),
	  status VARCHAR(64) DEFAULT 'Active',
	  PRIMARY KEY (season_id)
	);
	*/

	public Integer getSeasonId() {
		return season_id;
	}
	public void setSeasonId(Integer season_id) {
		this.season_id = season_id;
	}

	//Date getters and setters go here

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
