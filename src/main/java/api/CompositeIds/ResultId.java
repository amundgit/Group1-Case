package api.CompositeIds;

import javax.persistence.*;

import java.util.Objects;
import java.io.Serializable;

import api.Models.*;

@Embeddable
public class ResultId implements Serializable {
	//FOREIGN KEYS------------------------------------
  	@ManyToOne(fetch = FetchType.EAGER, optional=false)
  	@JoinColumn(name = "team_id", nullable = false)
  	private Team team;

  	@ManyToOne(fetch = FetchType.EAGER, optional=false)
  	@JoinColumn(name = "match_id", nullable = false)
  	private Match match;

	//CONSTRUCTORS
	public ResultId(){}

	public ResultId(Team team, Match match){
		this.team = team;
		this.match = match;
	}

	//GETTERS AND SETTERS
	public String getTeamId(){
    	return team.getId();
  	}
  	public void setTeamId(Team team){
    	this.team = team;
  	}
  	public Integer getMatchId(){
    	return match.getId();
  	}
  	public void setMatchId(Match match){
    	this.match = match;
  	}

	//OVERRIDES
	@Override
	public boolean equals(Object o){
		if(this == o){return true;}
		if(!(o instanceof ResultId)){return false;}
		ResultId that = (ResultId) o;
		return Objects.equals(getTeamId(), that.getTeamId()) && Objects.equals(getMatchId(), that.getMatchId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTeamId(), getMatchId());
	}
}