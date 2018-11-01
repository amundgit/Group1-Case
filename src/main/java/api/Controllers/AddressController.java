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

	@GetMapping(path = "/getbyaddress")
	public @ResponseBody Object getId(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			String address[] = body.get("address_line_1").toString().split(",");
			m.setMessage(addressRepository.getIdByAddress(address[0]).toString());
			return m;
		}
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
			String address_line_1 = body.get("address_line_1").toString();
			String address_line_2 = body.get("address_line_2").toString();
			String address_line_3 = body.get("address_line_3").toString();
			String postal_code = body.get("postal_code").toString();
			String city = body.get("city").toString();
			String country = body.get("country").toString();
			Address address = addressRepository.getByCompleteAddress(address_line_1, address_line_2, address_line_3, postal_code, city, country);
			if (address == null) {
				check = true;
				m.setMessage(address.getId().toString());
			}
			if (check) {
				Address a = new Address();
				a.setAddressLine1(address_line_1);
				a.setAddressLine2(address_line_2);
				a.setAddressLine3(address_line_3);
				a.setPostalCode(postal_code);
				a.setCity(city);
				a.setCountry(country);
				a = addressRepository.save(a);
				// Return the id the new address got in the database.
				m.setMessage(a.getId().toString());
			}
			return m;
		}
	}
}
