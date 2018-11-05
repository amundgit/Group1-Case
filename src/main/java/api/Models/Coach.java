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
public class Coach{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
    private Integer coach_id;

  	@ManyToOne(fetch = FetchType.EAGER, optional=false)
  	@JoinColumn(name = "person_id", nullable = false)
    private Person person;

	private String status = "active";

	public Integer getId() {
		return coach_id;
	}
	public void setId(Integer coach_id) {
		this.coach_id = coach_id;
	}

	public Integer getPersonId() {
		return person.getId();
	}

	public void setPersonId(Person person) {
		this.person = person;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Person getPerson(){
    	return person;
  	}
}

//SQL:
/*
CREATE TABLE COACH
(
  coach_id INTEGER NOT NULL,
  person_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (coach_id),
  FOREIGN KEY (person_id) REFERENCES PERSON(person_id)
);
*/
//Fetchtype should probably be eager