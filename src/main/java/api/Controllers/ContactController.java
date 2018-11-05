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
@RequestMapping("/contacts")
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private UserRepository userRepository;
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
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {
			boolean check = false;
			Contact contact = contactRepository.findByIDandDetails(Integer.parseInt(body.get("person_id").toString()),
					body.get("contact_detail").toString());
			if (contact == null) {
				check = true;
			}
			if (check) {
				Contact c = new Contact();
				c.setPersonId(personRepository.getById(Integer.parseInt(body.get("person_id").toString())));
				c.setContactType(body.get("contact_type").toString());
				c.setContactDetail(body.get("contact_detail").toString());
				contactRepository.save(c);
				m.setMessage("Success, Contact was created.");
				return m;
			} else {
				m.setError("Failure, Contact was not created.");
				return m;
			}
		}
	}

	@PostMapping(path = "/update")
	public @ResponseBody Object updateContact(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Integer contact_id = Integer.parseInt(body.get("contact_id").toString());
			Contact contact = contactRepository.findByContactId(contact_id);
			if (contact == null) {
				check = false;
				m.setError("Error: Invalid contact id");
			}
			if (check) {
				contact.setPersonId(personRepository.getById(Integer.parseInt(body.get("person_id").toString())));
				contact.setContactType(body.get("contact_type").toString());
				contact.setContactDetail(body.get("contact_detail").toString());
				contactRepository.save(contact);
				m.setMessage(contact.getId().toString());
			}
			return m;
		}
	}

	@PostMapping(path = "/delete")
	public @ResponseBody Object deleteContact(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),userRepository);
		if(m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Integer contact_id = Integer.parseInt(body.get("contact_id").toString());
			Contact contact = contactRepository.findByContactId(contact_id);
			if (contact == null) {
				check = false;
				m.setError("Error: Invalid contact id");
			}
			if (check) {
				contact.setStatus("inactive");
				contactRepository.save(contact);
				m.setMessage(contact.getId().toString());
			}
			return m;
		}
	}
}
