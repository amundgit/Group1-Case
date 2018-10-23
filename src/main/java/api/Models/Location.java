package api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Location {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer location_id;

    private String name;
    
    private String description;
    
    private Integer address_id; //Basic, needs to become foreign key
    
    private String status = "active";

	//SQL:
	/*
	CREATE TABLE LOCATION
	(
	  location_id INTEGER NOT NULL,
	  name VARCHAR(64) NOT NULL,
	  description VARCHAR(64),
	  address_id INTEGER NOT NULL,
	  status VARCHAR(64) DEFAULT 'Active',
	  PRIMARY KEY (location_id),
	  FOREIGN KEY (address_id) REFERENCES ADDRESS(address_id)
	);
	*/

	public Integer getLocationId() {
		return location_id;
	}
	public void setLocationId(Integer location_id) {
		this.location_id = location_id;
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

	//THIS NEEDS PROPER IMPLEMENTATION----------
	public Integer getAddressId(){
		return address_id;
	}
	public void setAddressId(Integer address_id){
		this.address_id = address_id;
	}
	//-------------------------------------------

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

