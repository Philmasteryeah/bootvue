package org.philmaster.bootvue.service;

import java.util.List;

import org.philmaster.bootvue.exception.UserNotFoundException;
import org.philmaster.bootvue.model.User;
import org.philmaster.bootvue.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public User findById(long id) {
		return userRepository.findById(id)
				.map(user -> {
					LOG.info("Reading user with id " + id + " from database.");
					return user;
				})
				.orElseThrow(() -> new UserNotFoundException(
						"The user with the id " + id + " couldn't be found in the database."));
	}

	public List<User> getAllUsers() {
		return userRepository.getAllUsers();
	}

	public User save(User user) {
		return userRepository.save(user);
	}

	public User save(String firstName, String lastName) {
		return userRepository.save(new User(firstName, lastName));
	}

}
