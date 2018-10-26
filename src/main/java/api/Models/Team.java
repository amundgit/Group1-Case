package api.Models;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;*/
import javax.persistence.*;//backup

@Entity // This tells Hibernate to make a table out of this class
public class Team{
	@Id
  //@GeneratedValue(strategy=GenerationType.AUTO)
  private String team_id;

  //FOREIGN KEYS --------------------------------
  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "owner_id", nullable = false)
  private Owner owner;

  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "association_id", nullable = false)
  private Association assocation;

  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "coach_id", nullable = false)
  private Coach coach;

  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;
  //---------------------------------------------

	private String status = "active";

  //BASIC CONSTRUCTOR
  public Team(){}

  public Team(String team_id){
    this.team_id = team_id;
  }

  //GETTERS AND SETTERS
  public String getId(){
    return team_id;
  }
  public void setId(String team_id){
    this.team_id = team_id;
  }
  //FOREIGN KEYS --------------------------------
  public Integer getOwnerId() {
    return owner.getId();
  }
  public void setOwnerId(Owner owner) {
    this.owner = owner;
  }

  public Integer getAssociationId() {
    return assocation.getId();
  }
  public void setAssociationId(Association assocation) {
    this.assocation = assocation;
  }

  public Integer getCoachId() {
    return coach.getId();
  }
  public void setCoachId(Coach coach) {
    this.coach = coach;
  }

  public Integer getLocationId() {
    return location.getId();
  }
  public void setLocationId(Location location) {
    this.location = location;
  }
  //---------------------------------------------
  public String getStatus(){
    return status;
  }
  public void setStatus(String status){
    this.status = status;
  }
}

//SQL:
/*
CREATE TABLE TEAM
(
  team_id VARCHAR(64) NOT NULL,
  owner_id INTEGER NOT NULL,
  association_id INTEGER NOT NULL,
  coach_id INTEGER NOT NULL,
  location_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (team_id),
  FOREIGN KEY (owner_id) REFERENCES OWNER(owner_id),
  FOREIGN KEY (association_id) REFERENCES ASSOCIATION(association_id),
  FOREIGN KEY (coach_id) REFERENCES COACH(coach_id),
  FOREIGN KEY (location_id) REFERENCES LOCATION(location_id)
);
*/