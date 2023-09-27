package com.springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springBoot.model.LogIn;

@Repository
public interface LoginRepo extends JpaRepository<LogIn, String> {

	
//	For checking login with custom Query both below Queries are working
//	@Query(value = "SELECT COUNT(*) FROM LogIn WHERE userName = ?1 AND password = ?2",nativeQuery = true)
	@Query(value = "SELECT COUNT(*) FROM LogIn WHERE userName = :userName AND password = :password",nativeQuery = true)
	public int logIn(String userName, String password);


//      For Admin login 
	@Query(value = "SELECT * FROM LogIn WHERE userName = :userName ",nativeQuery = true)
	public LogIn LoginDetails(String userName);
	
}
