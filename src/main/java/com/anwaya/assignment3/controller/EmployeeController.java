package com.anwaya.assignment3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.anwaya.assignment3.model.Employee;
import com.anwaya.assignment3.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/listEmployees")
	public String listEmployees(Model model) {
		// Get list of employees
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "listEmployees";
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// Create model attribute to bind form data
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "newEmployee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		// Save employee to database
		employeeService.saveEmployee(employee);
		return "redirect:/listEmployees?success";
	}
	
	@GetMapping("/showUpdateForm/{id}")
	public String showUpdateForm(@PathVariable(value="id") long id, Model model) {
		// Get employee from the service
		Employee employee = employeeService.getEmployeeById(id);
		
		// Set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);
		return "updateEmployee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value="id") long id) {
		// Call delete employee method
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/listEmployees";
	}
}
