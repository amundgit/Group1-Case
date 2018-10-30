package api.Models;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;*/
import javax.persistence.*;//backup

import org.hibernate.annotations.GenericGenerator;

@Entity // This tells Hibernate to make a table out of this class
public class Location {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
    private Integer location_id;

    private String name;
    
    private String description;
    
    @ManyToOne(fetch = FetchType.EAGER, optional=false)
  	@JoinColumn(name = "address_id", nullable = false)
    private Address address;
    
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

	public Integer getId() {
		return location_id;
	}
	public void setId(Integer location_id) {
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

	public Integer getAddressId(){
		return address.getId();
	}
	public void setAddressId(Address address){
		this.address = address;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}

