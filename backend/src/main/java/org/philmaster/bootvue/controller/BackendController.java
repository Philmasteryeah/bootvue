package org.philmaster.bootvue.controller;

import java.util.List;

import org.philmaster.bootvue.model.Account;
import org.philmaster.bootvue.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BackendController {

	private static final Logger LOG = LoggerFactory.getLogger(BackendController.class);

	public static final String HELLO_TEXT = "Hello from Spring Boot Backendasd!";
	public static final String SECURED_TEXT = "Hello from the secured resource!";

	@Autowired
	public UserServiceImpl userService;

	@RequestMapping("/login")
	public RedirectView login() {

		System.err.println("assfdasasssdsdd");
		return new RedirectView("/pages/login");
	}

	@GetMapping(path = "/hello")
	public String sayHello() {
		LOG.info("GET called on /hello resource");

		// generateImageFromPDF("src/main/resources/pdf.pdf", "png");

		return HELLO_TEXT;
	}

	@GetMapping(produces = "application/json", path = "/users")
	public @ResponseBody List<Account> getUsers() {
		LOG.info("GET called on /users resource");
		return userService.getAllUsers();
	}

	@PostMapping(path = "/user/{lastName}/{firstName}")
	@ResponseStatus(HttpStatus.CREATED)
	public long addNewUser(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) {
		Account savedUser = userService.save(firstName, lastName);
		LOG.info(savedUser.toString() + " successfully saved into DB");

		return savedUser.getId();
	}

	@GetMapping(path = "/user/{id}", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public Account getUserById(@PathVariable("id") long id) {
		return userService.findById(id);
	}

	@GetMapping(path = "/secured")
	public @ResponseBody String getSecured() {
		LOG.info("GET successfully called on /secured resource");
		return SECURED_TEXT;
	}

	// Forwards all routes to FrontEnd except: '/', '/index.html', '/api', '/api/**'
	// Required because of 'mode: history' usage in frontend routing, see README for
	// further details
	@RequestMapping(value = "{_:^(?!index\\.html|api).$}")
	public String redirectApi() {
		LOG.info("URL entered directly into the Browser, so we need to redirect...");
		return "forward:/";
	}

}
