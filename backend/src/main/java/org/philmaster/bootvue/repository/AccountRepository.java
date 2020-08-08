package org.philmaster.bootvue.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.philmaster.bootvue.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Account> findByLastName(@Param("lastname") String lastname);

	List<Account> findByFirstName(@Param("firstname") String firstname);

	public default List<Account> getAllUsers() {
		return StreamSupport.stream(this.findAll()
				.spliterator(), true)
				.collect(Collectors.toList());
	}

	// User findbyUsername(@Param("username") String username);

}
