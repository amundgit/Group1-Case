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

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@Controller // This means that this class is a Controller
@RequestMapping("/persons")
public class PersonController {	
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressRepository addressRepository;
	/**
	 * Get to show all Persons in the database
	 */
	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Person> getAllPersons() {
		return personRepository.findAll();
	}

	/**
	 * This method creates a new person if it does not exist and checks based on the
	 * name and birth.
	 * 
	 * @param body
	 * @return
	 */
	@PostMapping(path = "/add")
	public @ResponseBody Object addPerson(@RequestBody Map<String, Object> body) {
		boolean check = false;
		Messages msg = new Messages();
		String dateArr[] = body.get("date_of_birth").toString().split("-");
		LocalDate date = LocalDate.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]),
				Integer.parseInt(dateArr[2]));
		System.out.println(date);
		Person person = personRepository.findByFirstAndLastandBirth(body.get("first_name").toString(),
				body.get("last_name").toString(), date);
		if (person == null) {
			check = true;
		}
		if (check) {
			Person p = new Person();
			System.out.println(body.get("address_id").toString());
			p.setAddressId(addressRepository.getById(Integer.parseInt(body.get("address_id").toString())));
			p.setFirstName(body.get("first_name").toString());
			p.setLastName(body.get("last_name").toString());
			p.setDateOfBirth(date);
			personRepository.save(p);
			person = personRepository.findByFirstAndLastandBirth(p.getFirstName(), p.getLastName(), p.getDateOfBirth());
			System.out.println(person.getId().toString());
			msg.setMessage(person.getId().toString());
			return msg;
		} else {
			msg.setError("Failure, Person was not created.");
			return msg;
		}
	}

	// test
	@GetMapping(path = "/searchbyfirst")
	public @ResponseBody Iterable<Person> getAPersonByFirstName(@RequestParam String name) {
		return personRepository.findByFirstName(name);
	}

	@GetMapping(path = "/searchbylast")
	public @ResponseBody Iterable<Person> getAPersonByLastName(@RequestParam String name) {
		return personRepository.findByLastName(name);
	}

	@GetMapping(path = "/search")
	public @ResponseBody Person getAPersonByFirstAndLast(@RequestParam String firstName, @RequestParam String lastName,
			@RequestParam LocalDate bday) {
		return personRepository.findByFirstAndLastandBirth(firstName, lastName, bday);
	}
}