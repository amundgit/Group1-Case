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

	@Autowired
	UserRepository userRepository;

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Goal_type> getAllGoalTypes() {
		return goal_typeRepository.findAll();
	}

	@PostMapping(path = "/add")
	public @ResponseBody Object addGoalType(@RequestBody Map<String, Object> body) {
		Messages msg = new Messages();
		msg = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(), userRepository);
		if (msg.getRole() != 1) {
			return msg;
		} else {
			boolean check = true;
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

	@PostMapping(path = "/update")
	public @ResponseBody Object updateGoalType(@RequestBody Map<String, Object> body) {
		Messages msg = new Messages();
		msg = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(), userRepository);
		if (msg.getRole() != 1) {
			return msg;
		} else {
			boolean check = true;
			Integer goal_type_id = Integer.parseInt(body.get("goal_type_id").toString());
			Goal_type goaltype = goal_typeRepository.getById(goal_type_id);
			if(goaltype != null){
				check = false;
				msg.setError("Error: Invalid id");
			}
			if (check) {
				String type = body.get("type").toString();
				goaltype.setType(type);
				goal_typeRepository.save(goaltype);
				// Return the id the new address got in the database.
				msg.setMessage(goaltype.getId().toString());
			}
			return msg;
		}
	}

	@PostMapping(path = "/delete")
	public @ResponseBody Object deleteGoalType(@RequestBody Map<String, Object> body) {
		Messages msg = new Messages();
		msg = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(), userRepository);
		if (msg.getRole() != 1) {
			return msg;
		} else {
			boolean check = true;
			Integer goal_type_id = Integer.parseInt(body.get("goal_type_id").toString());
			Goal_type goaltype = goal_typeRepository.getById(goal_type_id);
			if(goaltype == null){
				check = false;
				msg.setError("Error: Invalid id");
			}
			if (check) {
				goaltype.setStatus("inactive");
				goal_typeRepository.save(goaltype);
				// Return the id the new address got in the database.
				msg.setMessage(goaltype.getId().toString());
			}
			return msg;
		}
	}
}