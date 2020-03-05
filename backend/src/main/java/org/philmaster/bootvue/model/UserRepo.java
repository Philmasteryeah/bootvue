package org.philmaster.bootvue.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends CrudRepository<User, Long> {

	List<User> findByLastName(@Param("lastname") String lastname);

	List<User> findByFirstName(@Param("firstname") String firstname);

	public default List<User> getAllUsers() {
		return StreamSupport.stream(this.findAll()
				.spliterator(), true)
				.collect(Collectors.toList());
	}
}
