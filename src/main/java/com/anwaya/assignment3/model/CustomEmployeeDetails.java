package com.anwaya.assignment3.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class CustomEmployeeDetails implements UserDetails {
	
	private Employee employee;

	public CustomEmployeeDetails(Employee employee) {
		this.employee = employee;
	}
	
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
 
    @Override
    public String getPassword() {
        return employee.getPassword();
    }
 
    @Override
    public String getUsername() {
        return employee.getEmail();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public String getFullName() {
        return employee.getFirstName() + " " + employee.getLastName();
    }

}
