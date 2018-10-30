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
public class Goal_type{
	@Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private Integer goal_type_id;

  private String type;

	private String status = "active";

	//GETTERS AND SETTERS
  public Integer getId() {
    return goal_type_id;
  }
  public void setId(Integer goal_type_id) {
    this.goal_type_id = goal_type_id;
  }

  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
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
CREATE TABLE GOAL_TYPE
(
  goal_type_id INTEGER NOT NULL,
  type VARCHAR(64) NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (goal_type_id)
);
*/