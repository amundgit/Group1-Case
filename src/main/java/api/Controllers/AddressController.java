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

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	@GetMapping(path = "/getbyid")
	public @ResponseBody Address getAddressById(@RequestParam Integer id) {
		return addressRepository.getById(id);
	}

	@GetMapping(path = "/getbyname")
	public @ResponseBody Integer getId(@RequestParam String address_line_1) {
		return addressRepository.getIdByAddress(address_line_1);
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
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			boolean check = false;
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
				m.setMessage(address.getId().toString());
			} else {
				m.setError("Failure, Address was not created.");
			}
			return m;
		}
	}
}