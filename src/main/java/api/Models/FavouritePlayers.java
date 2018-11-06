package api.Models;

import javax.persistence.*;//backup

import org.hibernate.annotations.GenericGenerator;

@Entity // This tells Hibernate to make a table out of this class
public class FavouritePlayers{
	@Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
  @GenericGenerator(name = "native", strategy = "native")
  private Integer favourite_id;

  //FOREIGN KEYS-------------------------------------
  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.EAGER, optional=false)
  @JoinColumn(name = "player_id", nullable = false)
  private Player player;
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

  public Integer getPlayerId() {
    return player.getId();
  }
  public void setPlayerId(Player player) {
    this.player = player;
  }
  //-------------------------------------------------
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }

  public Player getPlayer(){
    return player;
  }
}
