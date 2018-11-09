package api.Models;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;*/
import javax.persistence.*;//backup

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "match_table")
public class Match {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private Integer match_id;

  private LocalDate match_date;

  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "season_id", nullable = false)
  private Season season;

  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;

  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "home_team_id", nullable = false)
  private Team home_team;

  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "away_team_id", nullable = false)
  private Team away_team;

  private String status = "active";

  //GETTERS AND SETTERS
  public Integer getId() {
    return match_id;
  }
  public void setId(Integer match_id) {
    this.match_id = match_id;
  }
  //-----CHANGE DATATYPE LATER?-----------------
  public LocalDate getMatchDate() {
    return match_date;
  }
  public void setMatchDate(LocalDate match_date) {
    this.match_date = match_date;
  }
  //-------------------------------------------
  //FOREIGN KEYS
  public Integer getSeasonId(){
    return season.getId();
  }
  public void setSeasonId(Season season){
    this.season = season;
  }

  public Integer getLocationId(){
    return location.getId();
  }
  public void setLocationId(Location location){
    this.location = location;
  }

  public String getHomeTeamId(){
    return home_team.getId();
  }
  public void setHomeTeamId(Team home_team){
    this.home_team = home_team;
  }

  public String getAwayTeamId(){
    return away_team.getId();
  }
  public void setAwayTeamId(Team away_team){
    this.away_team = away_team;
  }
  //-------------------------------------------
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

  //test
  public Season getSeason(){
    return season;
  }

  public Location getLocation(){
    return location;
  }
}

//SQL:
/*
CREATE TABLE MATCH
(
  match_id INTEGER NOT NULL,
  match_date DATE NOT NULL,
  season_id INTEGER NOT NULL,
  location_id INTEGER NOT NULL,
  home_team_id INTEGER NOT NULL,
  away_team_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (match_id),
  FOREIGN KEY (season_id) REFERENCES SEASON(season_id),
  FOREIGN KEY (location_id) REFERENCES LOCATION(location_id),
  FOREIGN KEY (home_team_id) REFERENCES TEAM(team_id),
  FOREIGN KEY (away_team_id) REFERENCES TEAM(team_id)
);
*/