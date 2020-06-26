package com.avalith.votes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avalith.votes.model.Employee;
import com.avalith.votes.repository.EmployeeRepository;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(String id) {
		return employeeRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	@Transactional
	public void delete(String id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public Employee findByUserName(String userName) {
		return employeeRepository.findByUserName(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee emp = employeeRepository.findByUserName(username);

		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority("ADMIN"));
		roles.add(new SimpleGrantedAuthority("EMPLOYEE"));

		UserDetails userDet = new User(emp.getUserName(), emp.getPassword(), roles);
		return userDet;
	}

}
