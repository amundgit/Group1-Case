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
@RequestMapping("/goaltypes")
public class Goal_typeController {
	@Autowired
	Goal_typeRepository goal_typeRepository;

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Goal_type> getAllGoalTypes() {
		return goal_typeRepository.findAll();
	}

	@PostMapping(path = "/add")
	public @ResponseBody Object addGoalType(@RequestBody Map<String, Object> body) {
		boolean check = true;
		Messages msg = new Messages();
		String type = body.get("type").toString();
		Goal_type existenceCheck = goal_typeRepository.getByType(type);
		if(existenceCheck != null){
			check = false;
		}
		if (check) {
			Goal_type gt = new Goal_type();
			gt.setType(type);
			goal_typeRepository.save(gt);
			// Return the id the new address got in the database.
			msg.setMessage(gt.getId().toString());
		} else {
			msg.setError("Failure, goal type was not created.");
		}
		return msg;
	}
}