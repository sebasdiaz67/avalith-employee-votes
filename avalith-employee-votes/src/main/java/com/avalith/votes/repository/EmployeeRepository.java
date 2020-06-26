package com.avalith.votes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.avalith.votes.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

	public Employee findByUserName(String userName);
	
	@Query("select e from Employee e where e.userName != ?1")
	public List<Employee> findAllEmployees(String username);
}
