package api.Repositories;

import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import api.Models.Team;

import java.util.List;


public interface TeamRepository extends CrudRepository<Team, String> {

	@Query("SELECT t FROM Team t WHERE LOWER(t.team_id) = LOWER(:team_id) AND t.status = \'active\'")
	Team getByTeamId(@Param("team_id")String team_id);
}