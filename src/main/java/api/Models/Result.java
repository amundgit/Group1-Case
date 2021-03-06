package api.Models;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;*/
import javax.persistence.*;//backup

import api.CompositeIds.ResultId;

@Entity // This tells Hibernate to make a table out of this class
public class Result{
	@EmbeddedId
  private ResultId result_id;

  private String score;

  private String result;

	private String status = "active";

  //CONSTRUCTORS
  public Result(){};

  //GETTERS AND SETTERS
  public ResultId getId(){
    return result_id;
  }
  public void setId(ResultId result_id){
    this.result_id = result_id;
  }

  public String getScore(){
    return score;
  }
  public void setScore(String score){
    this.score = score;
  }

  public String getResult(){
    return result;
  }
  public void setResult(String result){
    this.result = result;
  }

  public String getStatus(){
    return status;
  }
  public void setStatus(String status){
    this.status = status;
  }
}

//SQL:
/*
CREATE TABLE RESULT
(
  score VARCHAR(4) NOT NULL,
  result VARCHAR(4) NOT NULL,
  match_id INTEGER NOT NULL,
  team_id INTEGER NOT NULL,
  status VARCHAR(64) DEFAULT 'Active',
  PRIMARY KEY (match_id),
  FOREIGN KEY (match_id) REFERENCES "MATCH"(match_id),
  FOREIGN KEY (team_id) REFERENCES TEAM(team_id)
);
*/