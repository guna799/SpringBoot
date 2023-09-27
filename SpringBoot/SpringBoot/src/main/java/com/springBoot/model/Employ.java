package com.springBoot.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="Employ")
public class Employ {
	
	@Id
	@Column(name="Empno")
	private String empNo;
	
	@Column(name="name") 	
	@Nonnull
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name="Gender")
	@Nonnull
	private Gender gender;
	
	@Column(name="Dept")
	@Nonnull
	private String dept;
	
	@Column(name="Desig")
	@Nonnull
	private String desig;
	
	@Column(name="Basic")
	@Nonnull
	private Long salary;
	
	@Column(name="status")
	@Nonnull
	private String status;
	
	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDesig() {
		return desig;
	}

	public void setDesig(String desig) {
		this.desig = desig;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Employ(String empNo, String name, Gender gender, String dept, String desig, Long salary, String status) {
		super();
		this.empNo = empNo;
		this.name = name;
		this.gender = gender;
		this.dept = dept;
		this.desig = desig;
		this.salary = salary;
		this.status = status;
	}

	public Employ() {
		super();
		// TODO Auto-generated constructor stub
	}

}
