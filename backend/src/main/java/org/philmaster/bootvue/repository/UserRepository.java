package org.philmaster.bootvue.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.philmaster.bootvue.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByLastName(@Param("lastname") String lastname);

	List<User> findByFirstName(@Param("firstname") String firstname);

	public default List<User> getAllUsers() {
		return StreamSupport.stream(this.findAll()
				.spliterator(), true)
				.collect(Collectors.toList());
	}

	User findbyUsername(String username);

}
