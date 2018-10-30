package api.Models;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;*/
import javax.persistence.*;
import java.time.*;

import org.hibernate.annotations.GenericGenerator;

@Entity // This tells Hibernate to make a table out of this class
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Integer person_id;

	// seems to work
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "address_id", nullable = false)
	private Address address;

	private String first_name;

	private String last_name;

	private LocalDate date_of_birth; // datatype just for testing

	public Integer getId() {
		return person_id;
	}

	public void setId(Integer person_id) {
		this.person_id = person_id;
	}

	/*
	 * public Integer getAddressId() { return address_id; }
	 * 
	 * public void setAddressId(Integer address_id) { this.address_id = address_id;
	 * }
	 */

	public Integer getAddressId() {
		return address.getId();
	}

	public void setAddressId(Address address) {
		this.address = address;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public LocalDate getDateOfBirth() {
		return date_of_birth;
	}

	public void setDateOfBirth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

}