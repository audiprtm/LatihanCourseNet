package com.coursenet.latihan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.coursenet.latihan.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	
	@Query(
			value="SELECT * FROM users where user_name=:userName"
					+ " and password=:password",
			nativeQuery=true
	)
	Optional<Users> findByUserNameAndPassword(
			@Param("userName")String userName, 
			@Param("password") String hashPassword);
}
