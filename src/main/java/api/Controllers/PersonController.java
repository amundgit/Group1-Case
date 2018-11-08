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
@RequestMapping("/persons")
public class PersonController {
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Get to show all Persons in the database
	 */
	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Person> getAllPersons() {
		return personRepository.findAll();
	}

	@GetMapping(path = "/getallactive")
	public @ResponseBody Iterable<Person> getAllActivePersons() {
		return personRepository.getAllActive();
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

		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			boolean check = false;
			String dateArr[] = body.get("date_of_birth").toString().split("-");
			LocalDate date = LocalDate.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]),
					Integer.parseInt(dateArr[2]));
			Person person = personRepository.findByFirstAndLastandBirth(body.get("first_name").toString(),
					body.get("last_name").toString(), date);
			if (person == null) {
				check = true;
			}
			if (check) {
				Person p = new Person();
				p.setAddressId(addressRepository.getById(Integer.parseInt(body.get("address_id").toString())));
				p.setFirstName(body.get("first_name").toString());
				p.setLastName(body.get("last_name").toString());
				p.setDateOfBirth(date);
				personRepository.save(p);
				person = personRepository.findByFirstAndLastandBirth(p.getFirstName(), p.getLastName(),
						p.getDateOfBirth());
				m.setMessage("Success, Person was created.");
				return m;
			} else {
				m.setError("Failure, Person was not created.");
				return m;
			}

		}
	}

	/**
	 * This method creates a new person if it does not exist and checks based on the
	 * name and birth.
	 * 
	 * @param body
	 * @return
	 */
	@PostMapping(path = "/getaddress")
	public @ResponseBody Object getAddress(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		boolean check = false;
		System.out.println(body);
		System.out.println(body.get("first_name").toString());
		System.out.println(body.get("last_name").toString());
		System.out.println(body.get("date_of_birth").toString());

		String dateArr[] = body.get("date_of_birth").toString().split("-");
		LocalDate date = LocalDate.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]),
				Integer.parseInt(dateArr[2]));
		Address address = personRepository.getAddressByPerson(body.get("first_name").toString(),
				body.get("last_name").toString(), date);
		System.out.println(address);
		if (address != null) {
			check = true;
		}
		if (check) {
			return address;
		} else {
			m.setError("Failure, address was not retrived.");
			return m;
		}
	}

	/**
	 * This method creates a new person if it does not exist and checks based on the
	 * name and birth.
	 * 
	 * @param body
	 * @return
	 */
	@PostMapping(path = "/update")
	public @ResponseBody Object getbyid(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			boolean check = false;
			System.out.println(body);
			System.out.println(body.get("id").toString());
			String dateArr[] = body.get("date_of_birth").toString().split("-");
			LocalDate date = LocalDate.of(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]),
					Integer.parseInt(dateArr[2]));
			Person person = personRepository.getById(Integer.parseInt(body.get("id").toString()));
			System.out.println(person);
			if (person != null) {
				check = true;
			}
			if (check) {
				person.setAddressId(addressRepository.getById(Integer.parseInt(body.get("address_id").toString())));
				person.setFirstName(body.get("first_name").toString());
				person.setLastName(body.get("last_name").toString());
				person.setDateOfBirth(date);
				personRepository.save(person);
				m.setMessage("Success, Person was updated.");
				return m;
			} else {
				m.setError("Error, person not found.");
				return m;
			}
		}

	}

	@PostMapping(path = "/delete")
	public @ResponseBody Object deletePerson(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			boolean check = false;
			Person person = personRepository.getById(Integer.parseInt(body.get("id").toString()));
			if (person != null) {
				check = true;
			}
			if (check) {
				person.setStatus("inactive");
				personRepository.save(person);
				m.setMessage("Success, Person was deleted.");
				return m;
			} else {
				m.setError("Error, person not found.");
				return m;
			}
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