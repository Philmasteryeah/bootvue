package org.philmaster.bootvue.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.philmaster.bootvue.model.Authority;
import org.philmaster.bootvue.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	public Authority findbyName(String name) {
		return authorityRepository.findByName(name)
				.get(0);
	}

	public List<Authority> getAllAuthorities() {
		return StreamSupport.stream(authorityRepository.findAll()
				.spliterator(), true)
				.collect(Collectors.toList());
	}

}
