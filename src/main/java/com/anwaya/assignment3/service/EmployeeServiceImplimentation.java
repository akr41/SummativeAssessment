package com.anwaya.assignment3.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anwaya.assignment3.model.CustomEmployeeDetails;
import com.anwaya.assignment3.model.Employee;
import com.anwaya.assignment3.repository.EmployeeRepository;

@Service
public class EmployeeServiceImplimentation implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	@Override
	public void saveEmployee(Employee employee) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		this.employeeRepository.save(employee);
	}
	
	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();
		} else {
			throw new RuntimeException("Employee not found for id: " + id);
		}
		return employee;
	}
	
	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByEmail(username);
		if (employee == null)
			throw new UsernameNotFoundException("Invalid username or password");
		return new CustomEmployeeDetails(employee);
	}
}
