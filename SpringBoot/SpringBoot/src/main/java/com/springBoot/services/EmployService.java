package com.springBoot.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.controller.MyController;
import com.springBoot.model.Employ;
import com.springBoot.model.LogIn;
import com.springBoot.repository.EmployRepo;
import com.springBoot.repository.LoginRepo;

@Service
public class EmployService {

	private static final Logger LOG = Logger.getLogger(EmployService.class.getName());

	@Autowired
	EmployRepo employRepo;

	public List<Employ> getActiveEmploy() {

		List<Employ> employList = new ArrayList<>();
		employList=employRepo.getActiveEmploy();
		//employRepo.findAll().forEach(todo -> employList.add(todo));

		return employList;
	}

	public List<Employ> findEmployById(String empNo) {
		List<Employ> empList= employRepo.findByEmpNo(empNo);
		return empList;
	}

	public String genarateEmpID() {

		int totalEmpNo=employRepo.genarateEmpID()+1;

		if (totalEmpNo < 10) {
	        return "EHYD00" + totalEmpNo;
	    } else if (totalEmpNo < 100) {
	        return "EHYD0" + totalEmpNo;
	    } else if (totalEmpNo < 1000) {
	        return "EHYD" + totalEmpNo;
	    }
	    return "";
	}

	public void save(Employ employ) {
		
		employRepo.save(employ);
	}

	public List<Employ> searchEmploy(String empNo) {//Employ or  List<Employ>
		// TODO Auto-generated method stub
		List<Employ> employList=employRepo.findByEmpNo(empNo);
//		Employ employ=employRepo.findByEmpNo(empNo).get(0);
//		LOG.info("62 -> "+employ.getName());
//		employ=employRepo.getByEmpNo(empNo);
//		LOG.info("64 ->"+employ.getName());
		return employList;
	}

	public void updateEmploy(Employ employ) {
		// TODO Auto-generated method stub
		this.save(employ);
		
	}

	public List<Employ> getAllEmployes() {
		// TODO Auto-generated method stub
		List<Employ> employList = new ArrayList<>();
		employList=employRepo.findAll();
		return employList;
	}

	

}
