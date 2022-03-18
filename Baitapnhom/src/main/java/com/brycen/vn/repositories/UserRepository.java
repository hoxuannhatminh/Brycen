package com.brycen.vn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brycen.vn.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CrudRepository<User, Long> {
	
	@Query(value = "select * from users where username=?1 and password =?2",nativeQuery = true)
	User getByUsernameAndPassword(String username, String password);
	
	User findByUsername(String username);
	
	
}
