package org.philmaster.bootvue.repository;

import java.util.List;

import org.philmaster.bootvue.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

	List<Authority> findByName(@Param("name") String name);

}
