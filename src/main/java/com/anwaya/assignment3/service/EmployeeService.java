package com.anwaya.assignment3.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.anwaya.assignment3.model.Employee;

public interface EmployeeService extends UserDetailsService{
	List<Employee> getAllEmployees();
	
	void saveEmployee(Employee employee);
	
	Employee getEmployeeById(long id);
	
	void deleteEmployeeById(long id);
}
