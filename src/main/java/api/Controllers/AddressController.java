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
@RequestMapping("/addresses")
public class AddressController {
	@Autowired
	private AddressRepository addressRepository;

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	@GetMapping(path = "/getbyid")
	public @ResponseBody Address getAddressById(@RequestParam Integer id) {
		return addressRepository.getById(id);
	}

	/**
	 * This method creates a new address if it does not exist and checks based on
	 * the first address line.
	 * 
	 * @param newAddress
	 * @return
	 */
	@PostMapping(path = "/add")
	public @ResponseBody Object addAddress(@RequestBody Map<String, Object> body) {
		boolean check = false;
		Messages msg = new Messages();
		Address address = addressRepository.getByAddress(body.get("address_line_1").toString());
		if (address == null) {
			check = true;
		}
		if (check) {
			Address a = new Address();
			a.setAddressLine1(body.get("address_line_1").toString());
			a.setAddressLine2(body.get("address_line_2").toString());
			a.setAddressLine3(body.get("address_line_3").toString());
			a.setPostalCode(body.get("postal_code").toString());
			a.setCity(body.get("city").toString());
			a.setCountry(body.get("country").toString());
			addressRepository.save(a);
			// Return the id the new address got in the database.
			address = addressRepository.getByAddress(a.getAddressLine1());
			System.out.println(address.getId().toString());
			msg.setMessage(address.getId().toString());
		} else {
			msg.setError("Failure, Address was not created.");
		}
		return msg;
	}
}