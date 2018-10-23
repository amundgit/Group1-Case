package api.Models;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;*/
import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Coach{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

  	@ManyToOne(fetch = FetchType.EAGER, optional=false)
  	@JoinColumn(name = "person_id", nullable = false)
  	//@JsonIgnore
    private Person person; //needs to be made into foreign key

	private String status = "active";

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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