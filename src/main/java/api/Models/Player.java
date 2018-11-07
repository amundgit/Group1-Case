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
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private Integer player_id;

  private String normal_position;

  private Integer number;

  // FOREIGN KEYS-------------------------------------
  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "person_id", nullable = false)
  private Person person;

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  @JoinColumn(name = "team_id", nullable = false)
  private Team team;
  // -------------------------------------------------
  private String status = "active";

  // GETTERS AND SETTERS
  public Integer getId() {
    return player_id;
  }

  public void setId(Integer player_id) {
    this.player_id = player_id;
  }

  public String getNormalPosition() {
    return normal_position;
  }

  public void setNormalPosition(String normal_position) {
    this.normal_position = normal_position;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  // FOREIGN KEYS-------------------------------------
  public Integer getPersonId() {
    return person.getId();
  }

  public void setPersonId(Person person) {
    this.person = person;
  }

  public String getTeamId() {
    return team.getId();
  }

  public void setTeamId(Team team) {
    this.team = team;
  }

  // -------------------------------------------------
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getName(){
    return person.getFirstName() + " " + person.getLastName();
  }
  public Person getPerson() {
    return person;
  }
}

// SQL:
/*
 * CREATE TABLE PLAYER ( player_id INTEGER NOT NULL, normal_position
 * VARCHAR(64), number VARCHAR(8), person_id INTEGER NOT NULL, team_id INTEGER
 * NOT NULL, status VARCHAR(64) DEFAULT 'Active', PRIMARY KEY (player_id),
 * FOREIGN KEY (person_id) REFERENCES PERSON(person_id), FOREIGN KEY (team_id)
 * REFERENCES TEAM(team_id) );
 */