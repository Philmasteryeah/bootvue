package org.philmaster.bootvue.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.philmaster.bootvue.exception.UserNotFoundException;
import org.philmaster.bootvue.model.User;
import org.philmaster.bootvue.model.UserRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
public class BackendController {

	private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

	public static final String HELLO_TEXT = "Hello from Spring Boot Backend!";
	public static final String SECURED_TEXT = "Hello from the secured resource!";

	@Autowired
	private UserRepo userRepository;

	@GetMapping(path = "/hello")
	public String sayHello() {
		LOG.info("GET called on /hello resource");
		return HELLO_TEXT;
	}

	@GetMapping(produces = "application/json", path = "/users")
	public @ResponseBody List<User> getUsers() {
		LOG.info("GET called on /users resource");
		return userRepository.getAllUsers();
	}

	@PostMapping(path = "/user/{lastName}/{firstName}")
	@ResponseStatus(HttpStatus.CREATED)
	public long addNewUser(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) {
		User savedUser = userRepository.save(new User(firstName, lastName));

		LOG.info(savedUser.toString() + " successfully saved into DB");

		return savedUser.getId();
	}

	@GetMapping(path = "/user/{id}")
	public User getUserById(@PathVariable("id") long id) {

		return userRepository.findById(id)
				.map(user -> {
					LOG.info("Reading user with id " + id + " from database.");
					return user;
				})
				.orElseThrow(() -> new UserNotFoundException(
						"The user with the id " + id + " couldn't be found in the database."));
	}

	@GetMapping(path = "/secured")
	public @ResponseBody String getSecured() {
		LOG.info("GET successfully called on /secured resource");
		return SECURED_TEXT;
	}

	// Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
	// Required because of 'mode: history' usage in frontend routing, see README for
	// further details
	@GetMapping(value = "{_:^(?!index\\.html|api).*$}")
	public String redirectApi() {

		LOG.info("URL entered directly into the Browser, so we need tod redirect...");

		return "redirect:";
	}
}
