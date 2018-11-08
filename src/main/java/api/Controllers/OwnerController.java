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
@RequestMapping("/owners")
public class OwnerController {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Owner> getAllOwners() {
		return ownerRepository.findAll();
	}

	@GetMapping(path = "/getallactive")
	public @ResponseBody Iterable<Owner> getAllActiveOwners() {
		return ownerRepository.getAllActive();
	}

	@PostMapping(path = "/assign")
	public @ResponseBody Messages assignOwner(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {		
			boolean check = false;
			Integer person_id = Integer.parseInt(body.get("person_id").toString());
			Owner existenceCheck = ownerRepository.getByPersonId(person_id);
			if (existenceCheck == null) {
				check = true;
			}
			if (check){
				Owner o = new Owner();
				o.setPersonId(personRepository.getById(person_id));
				o = ownerRepository.save(o);
				m.setMessage("Success");
			} else {
				m.setError("Person already an owner");
			}
			return m;
		}
	}

	@PostMapping(path = "/update")
	public @ResponseBody Messages updateOwner(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {		
			boolean check = true;
			Integer owner_id = Integer.parseInt(body.get("owner_id").toString());
			Owner o = ownerRepository.getById(owner_id);
			if (o == null) {
				check = false;
				m.setError("Invalid owner id");
			}
			if (check){
				Integer person_id = Integer.parseInt(body.get("person_id").toString());
				o.setPersonId(personRepository.getById(person_id));
				o = ownerRepository.save(o);
				m.setMessage("Success");
			}
			return m;
		}
	}

	@PostMapping(path = "/delete")
	public @ResponseBody Messages deleteOwner(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {		
			boolean check = true;
			Integer owner_id = Integer.parseInt(body.get("owner_id").toString());
			Owner o = ownerRepository.getById(owner_id);
			if (o == null) {
				check = false;
				m.setError("Invalid owner id");
			}
			if (check){
				o.setStatus("inactive");
				o = ownerRepository.save(o);
				m.setMessage("Success");
			}
			return m;
		}
	}
}
