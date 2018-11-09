package api.CompositeIds;

import javax.persistence.*;

import java.util.Objects;
import java.io.Serializable;

import api.Models.*;

@Embeddable
public class Match_positionId implements Serializable {
	//FOREIGN KEYS------------------------------------
  	@ManyToOne(fetch = FetchType.EAGER, optional=false)
  	@JoinColumn(name = "player_id", nullable = false)
  	private Player player;

  	@ManyToOne(fetch = FetchType.EAGER, optional=false)
  	@JoinColumn(name = "match_id", nullable = false)
  	private Match match;

	//CONSTRUCTORS
	public Match_positionId(){}

	public Match_positionId(Player player, Match match){
		this.player = player;
		this.match = match;
	}

	//GETTERS AND SETTERS
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

  	public String getPlayerName(){
  		return player.getName();
  	}

  	public String getPlayerTeam(){
  		return player.getTeamId();
  	}

	//OVERRIDES
	@Override
	public boolean equals(Object o){
		if(this == o){return true;}
		if(!(o instanceof Match_positionId)){return false;}
		Match_positionId that = (Match_positionId) o;
		return Objects.equals(getPlayerId(), that.getPlayerId()) && Objects.equals(getMatchId(), that.getMatchId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPlayerId(), getMatchId());
	}
}