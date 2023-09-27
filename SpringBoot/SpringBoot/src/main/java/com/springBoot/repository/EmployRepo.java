package com.springBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springBoot.model.Employ;
import com.springBoot.model.Gender;

import java.util.List;


@Repository
public interface EmployRepo extends JpaRepository<Employ, String>{
	
	public List<Employ> findByEmpNo(String empNo);
	
	@Query(value="SELECT COUNT(empNo) FROM Employ",nativeQuery = true)
	public int genarateEmpID();

	@Query(value="insert into Employ (empNo,name, gender, dept, desig, salary, status) values (:empNo,:name,:gender,:dept,:desig,:salary,'a')"
			,nativeQuery = true)
	public void AddEmploy(String empNo, String name, Gender gender, String dept, String desig, Long salary);
	
	public Employ getByEmpNo(String empNo);
	
	@Query(value="SELECT u FROM Employ u where u.status='a'")
	public List<Employ> getActiveEmploy();


}
