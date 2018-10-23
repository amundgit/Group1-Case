package api.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer address_id;

	private String address_line_1;

	private String address_line_2;

	private String address_line_3;

	private String postal_code;

	private String city;

	private String country;

	private String status = "active";

	public Address() {
	}
	// SQL:
	/*
	 * CREATE TABLE ADDRESS ( address_id INTEGER NOT NULL, address_line_1
	 * VARCHAR(64) NOT NULL, address_line_2 VARCHAR(64), postal_code VARCHAR(8) NOT
	 * NULL, city VARCHAR(64) NOT NULL, country VARCHAR(64) NOT NULL, address_line_3
	 * VARCHAR(64), status VARCHAR(64) DEFAULT 'Active', PRIMARY KEY (address_id) );
	 */

	public Integer getId() {
		return address_id;
	}

	public void setId(Integer address_id) {
		this.address_id = address_id;
	}

	public String getAddressLine1() {
		return address_line_1;
	}

	public void setAddressLine1(String address_line_1) {
		this.address_line_1 = address_line_1;
	}

	public String getAddressLine2() {
		return address_line_2;
	}

	public void setAddressLine2(String address_line_2) {
		this.address_line_2 = address_line_2;
	}

	public String getAddressLine3() {
		return address_line_3;
	}

	public void setAddressLine3(String address_line_3) {
		this.address_line_3 = address_line_3;
	}

	public String getPostalCode() {
		return postal_code;
	}

	public void setPostalCode(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
