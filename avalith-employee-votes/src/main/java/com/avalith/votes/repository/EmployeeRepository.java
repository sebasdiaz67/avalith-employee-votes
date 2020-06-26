package com.avalith.votes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avalith.votes.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

	public Employee findByUserName(String userName);
}
