package api.Models;

import javax.persistence.*;//backup

import org.hibernate.annotations.GenericGenerator;

@Entity // This tells Hibernate to make a table out of this class
public class FavouriteTeams{
	@Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private Integer favourite_id;

  //FOREIGN KEYS-------------------------------------
  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "team_id", nullable = false)
  private Team team;
  //-------------------------------------------------
	private String status = "active";

  //GETTERS AND SETTERS
  public Integer getId() {
    return favourite_id;
  }
  public void setId(Integer player_id) {
    this.favourite_id = favourite_id;
  }
  //FOREIGN KEYS-------------------------------------
  public Integer getUserId() {
    return user.getId();
  }
  public void setUserId(User user) {
    this.user = user;
  }

  public String getTeamId() {
    return team.getId();
  }
  public void setTeamId(Team team) {
    this.team = team;
  }
  //-------------------------------------------------
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

  public Team getTeam(){
    return team;
  }
}