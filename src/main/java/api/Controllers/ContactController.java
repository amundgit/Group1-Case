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

@Controller // This means that this class is a Controller
@RequestMapping("/contacts")
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private PersonRepository personRepository;


	/**
	 * Get to show all contacts in the database
	 */
	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	/**
	 * This method creates a new contact if it does not exist and checks based on
	 * the contact_Details.
	 * 
	 * @param body
	 * @return
	 */
	@PostMapping(path = "/add")
	public @ResponseBody Object addContact(@RequestBody Map<String, Object> body) {
		boolean check = false;
		Messages msg = new Messages();

		Contact contact = contactRepository.findByIDandDetails(Integer.parseInt(body.get("person_id").toString()),
				body.get("contact_detail").toString());
		if (contact == null) {
			check = true;
		}
		if (check) {
			Contact c = new Contact();
			System.out.println(body.get("person_id"));
			c.setPersonId(personRepository.getById(Integer.parseInt(body.get("person_id").toString())));
			c.setContactType(body.get("contact_type").toString());
			c.setContactDetail(body.get("contact_detail").toString());
			contactRepository.save(c);
			msg.setMessage("Success, Contact was created.");
			return msg;
		} else {
			msg.setError("Failure, Contact was not created.");
			return msg;
		}
	}
}