package org.philmaster.bootvue.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.philmaster.bootvue.exception.UserNotFoundException;
import org.philmaster.bootvue.model.Authority;
import org.philmaster.bootvue.model.User;
import org.philmaster.bootvue.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	public User findById(long id) {
		return userRepository.findById(id)
				.map(user -> {
					LOGGER.info("Reading user with id " + id + " from database.");
					return user;
				})
				.orElseThrow(() -> new UserNotFoundException(
						"The user with the id " + id + " couldn't be found in the database."));
	}

	public User findbyUsername(String username) {
		// TODO
		return userRepository.findByFirstName(username)
				.get(0);
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

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findbyUsername(username);
		// TOOD
		Set<Authority> grantedAuthorities = new HashSet<>();
		for (GrantedAuthority role : user.getAuthorities()) {
			grantedAuthorities.add(new Authority(role.toString()));
		}

		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
				grantedAuthorities);
	}

}
