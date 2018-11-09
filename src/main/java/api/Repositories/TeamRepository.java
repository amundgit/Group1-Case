package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Team;

import java.util.List;


public interface TeamRepository extends CrudRepository<Team, String> {

	@Query("SELECT t FROM Team t WHERE LOWER(t.team_id) = LOWER(:team_id) AND t.status = \'active\'")
	Team getByTeamId(@Param("team_id")String team_id);

	@Query("SELECT t FROM Team t WHERE owner_id = :owner_id AND status = \'active\'")
	List<Team> getTeamsByOwner(@Param("owner_id")Integer owner_id);

	@Query("SELECT t FROM Team t WHERE coach_id = :coach_id AND status = \'active\'")
	List<Team> getTeamsByCoach(@Param("coach_id")Integer coach_id);

	@Query("SELECT t FROM Team t WHERE association_id = :association_id AND status = \'active\'")
	List<Team> getTeamsByAssociationId(@Param("association_id")Integer association_id);

	@Query("SELECT t FROM Team t WHERE location_id = :location_id AND status = \'active\'")
	List<Team> getTeamsByLocationId(@Param("location_id")Integer location_id);

	@Query("SELECT t FROM Team t WHERE status = \'active\'")
	List<Team> getAllActive();
}