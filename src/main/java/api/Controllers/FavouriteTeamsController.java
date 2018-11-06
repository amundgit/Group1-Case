package api.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;

//New imports
import api.Repositories.*;
import api.Models.*;
import api.Pojos.*;

//More imports
import api.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.time.*;

@CrossOrigin
@Controller // This means that this class is a Controller
@RequestMapping("/favouriteteams")
public class FavouriteTeamsController {
	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FavouriteTeamsRepository favouriteTeamsRepository;

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<FavouriteTeams> getAllFavouriteTeams() {
		return favouriteTeamsRepository.findAll();
	}

	@PostMapping(path = "/getallbyuser")
	public @ResponseBody Iterable<FavouriteTeams> getFavouriteTeamsByUser(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return null;
		} else {
			Integer user_id = Integer.parseInt(body.get("user_id").toString());
			return favouriteTeamsRepository.getAllByUser(user_id);
		}
	}

	@PostMapping(path = "/add")
	public @ResponseBody Messages addFavouriteTeam(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Integer user_id = Integer.parseInt(body.get("user_id").toString());
			String team_id = body.get("team_id").toString();
			FavouriteTeams existenceCheck = favouriteTeamsRepository.getByUserAndTeam(user_id,team_id);
			if (existenceCheck != null) {
				check = false;
				m.setMessage(existenceCheck.getId().toString());
			}
			if (check) {
				FavouriteTeams ft = new FavouriteTeams();
				ft.setUserId(userRepository.getById(user_id));
				ft.setTeamId(teamRepository.getByTeamId(team_id));
				favouriteTeamsRepository.save(ft);
				m.setMessage(ft.getId().toString());
			}
			return m;
		}
	}

	/*@PostMapping(path = "/update")
	public @ResponseBody Messages updateFavouriteTeam(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Integer favourite_id = Integer.parseInt(body.get("favourite_id").toString());
			FavouritePlayers favouriteplayer = favouritePlayersRepository.getById(favourite_id);
			if (favouriteplayer == null) {
				check = false;
				m.setError("Error: Invalid favourite id");
			}
			if (check) {
				Integer user_id = Integer.parseInt(body.get("user_id").toString());
				Integer player_id = Integer.parseInt(body.get("player_id").toString());
				favouriteplayer.setUserId(userRepository.getById(user_id));
				favouriteplayer.setPlayerId(playerRepository.getById(player_id));
				favouritePlayersRepository.save(favouriteplayer);
				m.setMessage(favouriteplayer.getId().toString());
			}
			return m;
		}
	}

	@PostMapping(path = "/delete")
	public @ResponseBody Messages deleteFavouriteTeam(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Integer favourite_id = Integer.parseInt(body.get("favourite_id").toString());
			FavouritePlayers favouriteplayer = favouritePlayersRepository.getById(favourite_id);
			if (favouriteplayer == null) {
				check = false;
				m.setError("Error: Invalid favourite id");
			}
			if (check) {
				favouriteplayer.setStatus("inactive");
				favouritePlayersRepository.save(favouriteplayer);
				m.setMessage(favouriteplayer.getId().toString());
			}
			return m;
		}
	}*/
}
