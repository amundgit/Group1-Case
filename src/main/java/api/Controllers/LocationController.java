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
@RequestMapping("/locations")
public class LocationController {
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Get to show all locations in the database
	 */
	@GetMapping(path = "/devgetall")
	public @ResponseBody Iterable<Location> getAllLocations() {
		return locationRepository.findAll();
	}

	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Location> getAllActiveLocations() {
		return locationRepository.getAllActive();
	}

	/**
	 * This method creates a new location if it does not exist and checks based on
	 * the name.
	 * 
	 * @param newLocation
	 * @return
	 */
	@PostMapping(path = "/add")
	public @ResponseBody Object addLocation(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Location location = locationRepository.getByName(body.get("name").toString());
			if (location != null) {
				check = false;
				m.setError("Error: Location already exist");
			}
			if (check) {
				Location l = new Location();
				l.setAddressId(addressRepository.getById(Integer.parseInt(body.get("address_id").toString())));
				l.setName(body.get("name").toString());
				l.setDescription(body.get("description").toString());
				locationRepository.save(l);
				m.setMessage("Success, Location was created");
			}
			return m;
		}
	}

	@PostMapping(path = "/update")
	public @ResponseBody Object updateLocation(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Integer location_id = Integer.parseInt(body.get("location_id").toString());
			Location location = locationRepository.getById(location_id);
			if (location == null) {
				check = false;
				m.setError("Error: Invalid ID");
			}
			if (check) {
				location.setAddressId(addressRepository.getById(Integer.parseInt(body.get("address_id").toString())));
				location.setName(body.get("name").toString());
				location.setDescription(body.get("description").toString());
				locationRepository.save(location);
				m.setMessage(location.getId().toString());
			}
			return m;
		}
	}

	@PostMapping(path = "/delete")
	public @ResponseBody Object deleteLocation(@RequestBody Map<String, Object> body) {
		Messages m = new Messages();
		m = SecurityUtil.verifySession(body.get("sessionid").toString(), body.get("sessionuser").toString(),
				userRepository);
		if (m.getRole() != 1) {
			return m;
		} else {
			boolean check = true;
			Integer location_id = Integer.parseInt(body.get("location_id").toString());
			Location location = locationRepository.getById(location_id);
			if (location == null) {
				check = false;
				m.setError("Error: Invalid ID");
			}
			if (check) {
				location.setStatus("inactive");
				locationRepository.save(location);
				m.setMessage(location.getId().toString());
			}
			return m;
		}
	}
}
