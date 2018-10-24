package api.Models;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;*/
import javax.persistence.*;//backup

@Entity // This tells Hibernate to make a table out of this class
public class Match_position{
	@Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer position_id;

  private String position;

  //FOREIGN KEYS------------------------------------
  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "player_id", nullable = false)
  private Player player;

  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "match_id", nullable = false)
  private Match match;
  //-------------------------------------------------

	private String status = "active";

  //GETTERS AND SETTERS
  public Integer getId() {
    return position_id;
  }
  public void setId(Integer position_id) {
    this.position_id = position_id;
  }

  public String getPosition(){
    return position;
  }
  public void setPosition(String position){
    this.position = position;
  }
  //FOREIGN KEYS------------------------------------
  public Integer getPlayerId(){
    return player.getId();
  }
  public void setPlayerId(Player player){
    this.player = player;
  }
  public Integer getMatchId(){
    return match.getId();
  }
  public void setMatchId(Match match){
    this.match = match;
  }
  //-------------------------------------------------
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