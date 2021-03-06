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

	@GetMapping(path = "/devgetall")
	public @ResponseBody Iterable<FavouriteTeams> getAllFavouriteTeams() {
		return favouriteTeamsRepository.findAll();
	}

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<FavouriteTeams> getAllActiveFavouriteTeams() {
		return favouriteTeamsRepository.getAllActive();
	}

	@PostMapping(path = "/getallbyuser")
	public @ResponseBody Iterable<FavouriteTeams> getFavouriteTeamsByUser(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		String sessionuser = body.get("sessionuser").toString();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getError() != null) {
			return null;
		} else {
			Integer user_id = userRepository.findIdByName(sessionuser);
			return favouriteTeamsRepository.getAllByUser(user_id);
		}
	}

	@PostMapping(path = "/add")
	public @ResponseBody Messages addFavouriteTeam(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		String sessionuser = body.get("sessionuser").toString();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getError() != null) {
			return m;
		} else {
			boolean check = true;
			Integer user_id = userRepository.findIdByName(sessionuser);
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

	@PostMapping(path = "/update")
	public @ResponseBody Messages updateFavouriteTeam(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		String sessionuser = body.get("sessionuser").toString();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getError() != null) {
			return m;
		} else {
			boolean check = true;
			Integer user_id = userRepository.findIdByName(sessionuser);
			Integer favourite_id = Integer.parseInt(body.get("favourite_id").toString());
			FavouriteTeams favouriteteam = favouriteTeamsRepository.getByUserAndId(user_id, favourite_id);
			if (favouriteteam == null) {
				check = false;
				m.setError("Error: Invalid favourite id for this user");
			}
			if (check) {
				String team_id = body.get("team_id").toString();
				favouriteteam.setUserId(userRepository.getById(user_id));
				favouriteteam.setTeamId(teamRepository.getByTeamId(team_id));
				favouriteTeamsRepository.save(favouriteteam);
				m.setMessage(favouriteteam.getId().toString());
			}
			return m;
		}
	}

	@PostMapping(path = "/delete")
	public @ResponseBody Messages deleteFavouriteTeam(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		String sessionuser = body.get("sessionuser").toString();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getError() != null) {
			return m;
		} else {
			boolean check = true;
			Integer user_id = userRepository.findIdByName(sessionuser);
			Integer favourite_id = Integer.parseInt(body.get("favourite_id").toString());
			FavouriteTeams favouriteteam = favouriteTeamsRepository.getByUserAndId(user_id, favourite_id);
			if (favouriteteam == null) {
				check = false;
				m.setError("Error: Invalid favourite id for this user");
			}
			if (check) {
				favouriteteam.setStatus("inactive");
				favouriteTeamsRepository.save(favouriteteam);
				m.setMessage("Successfully deleted");
			}
			return m;
		}
	}
}
