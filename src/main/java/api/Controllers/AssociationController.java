package api.Controllers;

import org.apache.tomcat.jni.Local;
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
@RequestMapping("/associations")
public class AssociationController {
	@Autowired
	private AssociationRepository associationRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/devgetall")
	public @ResponseBody Iterable<Association> getAllAssociations() {
		return associationRepository.findAll();
	}

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Association> getAllActiveAssociations() {
		return associationRepository.getAllActive();
	}

	@PostMapping(path = "/add")
	public @ResponseBody Object addAssociation(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			String name = body.get("name").toString();
			Association existenceCheck = associationRepository.getByName(name);
			if (existenceCheck != null) {
				check = false;
				m.setMessage(existenceCheck.getId().toString());
			}
			if (check) {
				Association a = new Association();
				String description = body.get("description").toString();
				a.setName(name);
				a.setDescription(description);
				associationRepository.save(a);
				m.setMessage("Success: Assosiation was created.");
			}
			return m;
		}
	}

	@PostMapping(path = "/update")
	public @ResponseBody Object updateAssociation(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Integer association_id = Integer.parseInt(body.get("association_id").toString());
			Association association = associationRepository.getById(association_id);
			if (association == null) {
				check = false;
				m.setError("Error: Non-existent association id");
			}
			if (check) {
				String description = body.get("description").toString();
				String name = body.get("name").toString();
				association.setName(name);
				association.setDescription(description);
				associationRepository.save(association);
				m.setMessage(association.getId().toString());
			}
			return m;
		}
	}

	@PostMapping(path = "/delete")
	public @ResponseBody Object deleteAssociation(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Integer association_id = Integer.parseInt(body.get("association_id").toString());
			Association association = associationRepository.getById(association_id);
			if (association == null) {
				check = false;
				m.setError("Error: Non-existent association id");
			}
			if (check) {
				association.setStatus("inactive");
				associationRepository.save(association);
				m.setMessage("Successfully deleted");
			}
			return m;
		}
	}
}
