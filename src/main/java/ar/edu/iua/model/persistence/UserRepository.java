package ar.edu.iua.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.iua.model.User;

//https://docs.spring.io/spring-data/jpa/docs/2.3.2.RELEASE/reference/html/#repositories.query-methods.details

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByUsernameOrEmail(String username, String email);
}


