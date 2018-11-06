package api.Models;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;*/
import javax.persistence.*;//backup

import org.hibernate.annotations.GenericGenerator;

import api.CompositeIds.Match_positionId;

@Entity // This tells Hibernate to make a table out of this class
public class Match_position{
	
  @EmbeddedId
  private Match_positionId position_id;

  private String position;

	private String status = "active";

  //GETTERS AND SETTERS
  public Match_positionId getId() {
    return position_id;
  }
  public void setId(Match_positionId position_id) {
    this.position_id = position_id;
  }

  public String getPosition(){
    return position;
  }
  public void setPosition(String position){
    this.position = position;
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
CREATE TABLE MATCH_POSITION
(
  position VARCHAR(64),
  player_id INTEGER NOT NULL,
  match_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  FOREIGN KEY (player_id) REFERENCES PLAYER(player_id),
  FOREIGN KEY (match_id) REFERENCES "MATCH"(match_id)
);
*/