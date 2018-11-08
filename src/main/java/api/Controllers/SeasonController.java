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
@RequestMapping("/seasons")
public class SeasonController {
	@Autowired
	private SeasonRepository seasonRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/devgetall")
	public @ResponseBody Iterable<Season> getAllSeasons() {
		return seasonRepository.findAll();
	}

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Season> getAllActiveSeasons() {
		return seasonRepository.getAllActive();
	}

	@PostMapping(path = "/add")
	public @ResponseBody Messages addSeason(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {
			boolean check = false;
			String startDateArr[] = body.get("start_date").toString().split("-");
			LocalDate start_date = LocalDate.of(Integer.parseInt(startDateArr[0]), Integer.parseInt(startDateArr[1]), Integer.parseInt(startDateArr[2]));
			String endDateArr[] = body.get("end_date").toString().split("-");
			LocalDate end_date = LocalDate.of(Integer.parseInt(endDateArr[0]), Integer.parseInt(endDateArr[1]), Integer.parseInt(endDateArr[2]));
			String name = body.get("name").toString();
			String description = body.get("description").toString();
			Season existenceCheck = seasonRepository.getByName(name);
			if (existenceCheck == null) {
				check = true;
			}
			if (check) {
				Season s = new Season();
				s.setStartDate(start_date);
				s.setEndDate(end_date);
				s.setName(name);
				s.setDescription(description);
				seasonRepository.save(s);
				m.setMessage("Success");
			} else {
				m.setError("Error: Season by that name already exists");
			}
			return m;
		}
	}

	@PostMapping(path = "/update")
	public @ResponseBody Messages updateSeason(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Integer season_id = Integer.parseInt(body.get("season_id").toString());
			Season s = seasonRepository.getById(season_id);
			if (s == null) {
				check = false;
				m.setError("Error: Invalid season id");
			}
			if (check) {
				String startDateArr[] = body.get("start_date").toString().split("-");
				LocalDate start_date = LocalDate.of(Integer.parseInt(startDateArr[0]), Integer.parseInt(startDateArr[1]), Integer.parseInt(startDateArr[2]));
				String endDateArr[] = body.get("end_date").toString().split("-");
				LocalDate end_date = LocalDate.of(Integer.parseInt(endDateArr[0]), Integer.parseInt(endDateArr[1]), Integer.parseInt(endDateArr[2]));
				String name = body.get("name").toString();
				String description = body.get("description").toString();
				s.setStartDate(start_date);
				s.setEndDate(end_date);
				s.setName(name);
				s.setDescription(description);
				seasonRepository.save(s);
				m.setMessage("Success");
			}
			return m;
		}
	}

	@PostMapping(path = "/delete")
	public @ResponseBody Messages deleteSeason(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Integer season_id = Integer.parseInt(body.get("season_id").toString());
			Season s = seasonRepository.getById(season_id);
			if (s == null) {
				check = false;
				m.setError("Error: Invalid season id");
			}
			if (check) {
				s.setStatus("inactive");
				seasonRepository.save(s);
				m.setMessage("Success");
			}
			return m;
		}
	}
}
