package com.springBoot.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springBoot.model.Employ;
import com.springBoot.model.LogIn;
import com.springBoot.services.EmployService;
import com.springBoot.services.LogInService;

@Controller
public class MyController {

	@Autowired
	private EmployService employService;

	@Autowired
	private LogInService logInService;

	private static final Logger LOG = Logger.getLogger(MyController.class.getName());

	@GetMapping("/")
	public String showIndexPage() {
		LOG.info("------------------> Spring Boot Started");
		return "index";
	}

	@GetMapping(value = "/login")
	public String login(Model model) {
		model.addAttribute("login", new LogIn());
		return "login";

	}

	@PostMapping(value = "/loginDetails")
	public ModelAndView loginDetails(@ModelAttribute("login") LogIn login, ModelAndView model) {

		boolean isLogin = logInService.login(login.getUserName(), login.getPassword());
		LogIn loginDetails=logInService.loginDetails(login.getUserName());
		List<Employ> listEmploy = null;
		if (isLogin) {
			model.addObject("login", login);
			if(loginDetails.getAccess().equalsIgnoreCase("admin")) {
				model.addObject("errMsg","Invalid Credentials");
				LOG.info("Admin Log in ");
				listEmploy = employService.getAllEmployes();
				model.addObject("listEmploy", listEmploy);
				model.setViewName("Admin");
				return model;
			}
			LOG.info("Normal Log in ");
			return new ModelAndView("redirect:/searchemploy");
		}
		model.addObject("login", login);
		model.addObject("errMsg","Invalid Credentials");
		model.setViewName("login");
		return model;
	}

	@GetMapping(value = "/searchemploy")
	public String search(@ModelAttribute("employ") Employ employ) {
		return "SearchEmploy";
	}

	@PostMapping(value = "/result")
	public ModelAndView listEmploy(ModelAndView model, HttpServletRequest req, @RequestParam String clickedButton)
			throws IOException {
		if (!"Reset".equals(clickedButton)) {

			List<Employ> listEmploy = null;
			String empNo = null;

			if (req.getParameter("empNo") != null && !req.getParameter("empNo").equals("")) {

				empNo = req.getParameter("empNo");
				listEmploy = employService.findEmployById(empNo);
			} else {

				listEmploy = employService.getActiveEmploy();
			}

			if (listEmploy.size() > 0) {

				model.addObject("listEmploy", listEmploy);
				model.setViewName("ResultsPage");

				return model;
			} else {
				model.addObject("employ", new Employ());
				model.setViewName("SearchEmploy");
				return model;
			}
		}
		return new ModelAndView("redirect:/searchemploy");
	}

	@GetMapping(value = "/addemploy")
	public String addEmploy(Model model) {
		Employ employ = new Employ();
		employ.setEmpNo(employService.genarateEmpID());
		model.addAttribute("employ", employ);
		return "AddEmploy";

	}

	@PostMapping(value = "/newEmploy")
	public ModelAndView newEmploy(@ModelAttribute("employ") Employ employ, ModelAndView model) {
		boolean flag = true;
		if (employ.getName() == null || employ.getName() == "") {
			model.addObject("name", " Please Enter Employ Name <br>");
			flag = false;
		}
		if (employ.getGender() == null || String.valueOf(employ.getGender()) == "") {
			model.addObject("gndr", " Please Enter Employ Gender <br>");
			flag = false;
		}
		if (employ.getDept() == null || employ.getDept() == "") {
			model.addObject("dpt", " Please Enter Employ Department <br>");
			flag = false;
		}
		if (employ.getDesig() == null || employ.getDesig() == "") {
			model.addObject("dsg", " Please Enter Employ Designation <br>");
			flag = false;
		}
		if(employ.getSalary()==null || employ.getSalary()==0) {
			model.addObject("sal", " Please Enter Employ Salary <br>");
			flag = false;
		}
		if (!flag) {
			employ.setEmpNo(employService.genarateEmpID());
			model.addObject("employ", employ);
			model.setViewName("AddEmploy");
			return model;
		}
		employ.setStatus("a");
		employService.save(employ);
		return new ModelAndView("redirect:/searchemploy");

	}

	@GetMapping(value = "/editemploy")
	public ModelAndView editEmploy(ModelAndView model, HttpServletRequest req, @RequestParam("empno") String empNo) {
		List<Employ> listEmploy = null;
		Employ employ = new Employ();
		
		listEmploy = employService.searchEmploy(empNo);
		LOG.info("Employ object -------> " + listEmploy.get(0));
		employ = (Employ) listEmploy.get(0);
		model.addObject("employ", employ);
		model.setViewName("UpdateEmploy");
		return model;

	}

	@PostMapping(value = "/updateEmploy")
	public ModelAndView UpdateEmploy(@ModelAttribute("employ") Employ employ) {
		employService.updateEmploy(employ);
		return new ModelAndView("redirect:/searchemploy");

	}
	
	@GetMapping(value = "/adminEditemploy")
	public ModelAndView AdminEditEmploy(ModelAndView model, HttpServletRequest req, @RequestParam("empno") String empNo) {
		List<Employ> listEmploy = null;
		Employ employ = new Employ();
		
		listEmploy = employService.searchEmploy(empNo);
		LOG.info("Employ object -------> " + listEmploy.get(0).getEmpNo());
		employ = (Employ) listEmploy.get(0);
		model.addObject("employ", employ);
		model.setViewName("AdminUpdate");
		return model;

	}
	
	@PostMapping(value = "/adminUpdateEmploy")
	public ModelAndView AdminUpdateEmploy(@ModelAttribute("employ") Employ employ) {
		employService.updateEmploy(employ);
		ModelAndView model=new ModelAndView();
		List<Employ> listEmploy = null;
		listEmploy = employService.getAllEmployes();
		model.addObject("listEmploy", listEmploy);
		model.setViewName("Admin");
		return model;

	}


	@GetMapping("/allEmployees")
	public String viewAllToDoItems(Model model, @ModelAttribute("message") String message) {
		model.addAttribute("listEmploy", employService.getActiveEmploy());
		model.addAttribute("message", message);

		return "ResultsPage";
	}

	@PostMapping(value = "/logout")
	public ModelAndView logout(ModelAndView model) {
		return new ModelAndView("redirect:/login");

	}

	@PostMapping(value="/closeEmpSearch")
	public ModelAndView closeSearch(ModelAndView model){
		return new ModelAndView("redirect:/login");
	}

	@PostMapping(value = "/closeEmpResults")
	public ModelAndView closeResults(ModelAndView model) {
		return new ModelAndView("redirect:/searchemploy");

	}
}
