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

@Controller // This means that this class is a Controller
@RequestMapping("/associations")
public class AssociationController {	
	@Autowired
	private AssociationRepository associationRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Association> getAllAssociations() {
		return associationRepository.findAll();
	}

	// TEST - return other value?
	@PostMapping(path = "/add")
	public @ResponseBody Object addAssociation(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {
		 	boolean check = false;
		 	String name = body.get("name").toString(); 
		 	String description = body.get("description").toString(); 
		 	Association existenceCheck = associationRepository.getByName(name); 
		 	if (existenceCheck == null) { 
		 		check = true; 
		 	} 
		 	if (check) { 
		 		Association a = new Association(); 
		 		a.setName(name);
		  		a.setDescription(description); 
		  		associationRepository.save(a);
		  		m.setMessage("Success"); 
		  	} else {
		  		m.setError("Error: Association exists"); }
		  		return m;
		 }
	}
}