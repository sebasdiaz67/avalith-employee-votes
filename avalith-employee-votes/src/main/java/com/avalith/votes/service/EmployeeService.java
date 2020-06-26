package com.avalith.votes.service;

import java.util.List;

import com.avalith.votes.model.Employee;

public interface EmployeeService {

	public List<Employee> findAll();

	public Employee findById(String id);

	public Employee save(Employee employee);

	public void delete(String id);
	
	public Employee findByUserName(String userName);
}
