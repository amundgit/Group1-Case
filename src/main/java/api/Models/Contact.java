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
public class Contact{
	@Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private Integer contact_id;

  private String contact_type;

  private String contact_detail;

  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;

	private String status = "active";

  //GETTERS AND SETTERS
  public Integer getId() {
    return contact_id;
  }
  public void setId(Integer contact_id) {
    this.contact_id = contact_id;
  }

  public String getContactType(){
    return contact_type;
  }
  public void setContactType(String contact_type){
    this.contact_type = contact_type;
  }

  public String getContactDetail(){
    return contact_detail;
  }
  public void setContactDetail(String contact_detail){
    this.contact_detail = contact_detail;
  }

  public Integer getPersonId(){
    return person.getId();
  }
  public void setPersonId(Person person){
    this.person = person;
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
CREATE TABLE CONTACT
(
  contact_id INTEGER NOT NULL,
  contact_type VARCHAR(64) NOT NULL,
  contact_detail VARCHAR(64) NOT NULL,
  person_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (contact_id),
  FOREIGN KEY (person_id) REFERENCES PERSON(person_id)
);
*/