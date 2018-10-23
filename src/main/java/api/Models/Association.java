package api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Association{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer association_id;

    private String name;

    private String description;

    private String status = "active";
    //SQL:
	/*
	CREATE TABLE ASSOCIATION
	(
	  association_id INTEGER NOT NULL,
	  name VARCHAR(64) NOT NULL,
	  description VARCHAR(64) NOT NULL,
	  status VARCHAR(64) DEFAULT 'Active',
	  PRIMARY KEY (association_id)
	);
	*/

	public Integer getId(){
		return association_id;
	}
	public void setId(Integer association_id){
		this.association_id = association_id;
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