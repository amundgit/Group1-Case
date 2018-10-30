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
@RequestMapping("/locations")
public class LocationController {
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private LocationRepository locationRepository;

	/**
	 * Get to show all locations in the database
	 */
	@GetMapping(path = "/getall")
	public @ResponseBody Iterable<Location> getAllLocations() {
		return locationRepository.findAll();
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
		boolean check = false;
		Messages msg = new Messages();
		Location location = locationRepository.getByName(body.get("name").toString());
		if (location == null) {
			check = true;
		}
		if (check) {
			Location l = new Location();
			l.setAddressId(addressRepository.getById(Integer.parseInt(body.get("address_id").toString())));
			l.setName(body.get("name").toString());
			l.setDescription(body.get("description").toString());
			locationRepository.save(l);
			msg.setMessage("Success, Location was created.");
			return msg;
		} else {
			msg.setError("Failure, Location was not created.");
			return msg;
		}
	}
}