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
@RequestMapping("/owners")
public class OwnerController {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private OwnerRepository ownerRepository;

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Owner> getAllOwners() {
		return ownerRepository.findAll();
	}

	@PostMapping(path = "/assign")
	public @ResponseBody Messages assignOwner(@RequestBody Map<String, Object> body) {
		boolean check = false;
		Messages m = new Messages();
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