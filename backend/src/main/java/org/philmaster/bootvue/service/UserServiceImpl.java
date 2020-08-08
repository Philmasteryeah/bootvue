package org.philmaster.bootvue.service;

import java.util.List;

import org.philmaster.bootvue.exception.UserNotFoundException;
import org.philmaster.bootvue.model.Account;
import org.philmaster.bootvue.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;

	public Account findById(long id) {
		return accountRepository.findById(id)
				.map(user -> {
					LOGGER.info("Reading user with id " + id + " from database.");
					return user;
				})
				.orElseThrow(() -> new UserNotFoundException(
						"The user with the id " + id + " couldn't be found in the database."));
	}

	public Account findbyUsername(String username) {
		// TODO
		return accountRepository.findByFirstName(username)
				.get(0);
	}

	public List<Account> getAllUsers() {
		return accountRepository.getAllUsers();
	}

	public Account save(Account user) {
		return accountRepository.save(user);
	}

	public Account save(String firstName, String lastName) {
//		Set<Authority> authorities = new HashSet<>();
//		Authority authority = new Authority();
//		authorities.add(authority);

		Account account = new Account();
		account.setFirstName(firstName);
		account.setLastName(lastName);
		account.setLogin(firstName);
		//account.setAuthorities(authorities);
		

		return accountRepository.save(account);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return findbyUsername(username);
//
//		Set<Authority> authorities = new HashSet<>();
//		for (GrantedAuthority role : user.getAuthorities())
//			authorities.add(new Authority(role.toString()));
//
//		return new Account(username, user.getPassword(), authorities);
	}

}
