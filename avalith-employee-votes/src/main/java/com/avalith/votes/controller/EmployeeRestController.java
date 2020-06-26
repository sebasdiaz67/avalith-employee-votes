package com.avalith.votes.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avalith.votes.controller.vo.EmployeeVO;
import com.avalith.votes.model.Employee;
import com.avalith.votes.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "employee")
public class EmployeeRestController {

	private final Logger log = LoggerFactory.getLogger(EmployeeRestController.class);

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employees")
	@ApiOperation(value = "Find all", notes = "Service to find thea areas")
	public List<Employee> findAll() {
		log.info("muestra todos los empleados");
		return employeeService.findAll();
	}
	
	@GetMapping("/employees/{id}")
	@ApiOperation(value = "Find by id", notes = "Service to find the area by id")
	public ResponseEntity<?> findById(@PathVariable String id) {
		Employee employeeBDD = null;
		Map<String, Object> response = new HashMap<>();
		try {
			employeeBDD = employeeService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to get the data");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (employeeBDD == null) {
			response.put("mensaje", "Employee not found ".concat(" ID: ").concat(id));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(employeeBDD, HttpStatus.OK);
	}
	
	@PostMapping("/employees")
	@ApiOperation(value = "Create", notes = "Service to save the employee")
	public ResponseEntity<?> create(@RequestBody EmployeeVO employeeRequest) {
		Employee employee = null;
		Map<String, Object> response = new HashMap<>();
		try {
			Employee employeeToSave = new Employee();
			employeeToSave.setFirtsName(employeeRequest.getFirtsName());
			employeeToSave.setLastName(employeeRequest.getLastName());
			employeeToSave.setUserName(employeeRequest.getUserName());
			employeeToSave.setPassword(employeeRequest.getPassword());
			employeeToSave.setDateCreate(new Date());
			employeeToSave.setUserCreate(employeeRequest.getUserCreate());
			employee = employeeService.save(employeeToSave);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to save");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Saved");
		response.put("area", employee);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
