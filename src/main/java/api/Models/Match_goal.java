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
public class Match_goal{
	@Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private Integer goal_id;

  private String description;

  //FOREIGN KEYS-------------------------------------
  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "goal_type_id", nullable = false)
  private Goal_type goal_type;

  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "match_id", nullable = false)
  private Match match;

  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "player_id", nullable = false)
  private Player player;
  //-------------------------------------------------
	private String status = "active";

  //GETTERS AND SETTERS
  public Integer getId() {
    return goal_id;
  }
  public void setId(Integer goal_id) {
    this.goal_id = goal_id;
  }

  public String getDescription(){
    return description;
  }
  public void setDescription(String description){
    this.description = description;
  }
  //FOREIGN KEYS
  public Integer getGoalTypeId(){
    return goal_type.getId();
  }
  public void setGoalTypeId(Goal_type goal_type){
    this.goal_type = goal_type;
  }

  public Integer getMatchId(){
    return match.getId();
  }
  public void setMatchId(Match match){
    this.match = match;
  }

  public Integer getPlayerId(){
    return player.getId();
  }
  public void setPlayerId(Player player){
    this.player = player;
  }
  //----------------------------------------
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }
}

//SQL:
/*
CREATE TABLE MATCH_GOAL
(
  goal_id INTEGER NOT NULL,
  description VARCHAR(64),
  goal_type_id INTEGER NOT NULL,
  match_id INTEGER NOT NULL,
  player_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (goal_id),
  FOREIGN KEY (goal_type_id) REFERENCES GOAL_TYPE(goal_type_id),
  FOREIGN KEY (match_id) REFERENCES "MATCH"(match_id),
  FOREIGN KEY (player_id) REFERENCES PLAYER(player_id)
);
*/