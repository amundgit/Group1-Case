package api.Models;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;*/
import javax.persistence.*;//backup

import java.time.LocalDate; //will this create sql date?

@Entity // This tells Hibernate to make a table out of this class
public class Season{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer season_id;

    private LocalDate start_date;

    private LocalDate end_date;

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

	public Integer getId() {
		return season_id;
	}
	public void setId(Integer season_id) {
		this.season_id = season_id;
	}

	//Date getters and setters go here
	public LocalDate getStartDate(){
		return start_date;
	}
	public void setStartDate(LocalDate start_date){
		this.start_date = start_date;
	}

	public LocalDate getEndDate(){
		return end_date;
	}
	public void setEndDate(LocalDate end_date){
		this.end_date = end_date;
	}

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
