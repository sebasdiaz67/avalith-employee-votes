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

import com.avalith.votes.controller.vo.VoteVO;
import com.avalith.votes.model.Area;
import com.avalith.votes.model.Employee;
import com.avalith.votes.model.Vote;
import com.avalith.votes.service.AreaService;
import com.avalith.votes.service.EmployeeService;
import com.avalith.votes.service.VoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "vote")
public class VoteRestController {

	private final Logger log = LoggerFactory.getLogger(VoteRestController.class);

	@Autowired
	private VoteService voteService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/votes")
	@ApiOperation(value = "Find all", notes = "Service to find the votes")
	public List<Vote> findAll() {
		log.info("muestra todos los votos");
		return voteService.findAll();
	}

	@GetMapping("/votes/{id}")
	@ApiOperation(value = "Find by id", notes = "Service to find the vote by id")
	public ResponseEntity<?> findById(@PathVariable String id) {
		Vote voteBDD = null;
		Map<String, Object> response = new HashMap<>();
		try {
			voteBDD = voteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to get the data");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (voteBDD == null) {
			response.put("mensaje", "Vote not found ".concat(" ID: ").concat(id));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(voteBDD, HttpStatus.OK);
	}

	@PostMapping("/votes")
	@ApiOperation(value = "Create", notes = "Service to save the vote")
	public ResponseEntity<?> create(@RequestBody VoteVO voteRequest) {
		Vote vote = null;
		Area areaBDD = null;
		Employee employeeBDD = null;
		Map<String, Object> response = new HashMap<>();
		try {
			areaBDD = areaService.findById(voteRequest.getIdArea());
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to get the area data");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (areaBDD == null) {
			response.put("mensaje", "Area not found ".concat(" ID: ").concat(voteRequest.getIdArea()));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		try {
			employeeBDD = employeeService.findById(voteRequest.getIdEmployee());
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to get the employee data");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (employeeBDD == null) {
			response.put("mensaje", "Employee not found ".concat(" ID: ").concat(voteRequest.getIdArea()));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		try {
			Vote voteToSave = new Vote();
			voteToSave.setDescription(voteRequest.getDescription());
			voteToSave.setIsVote(voteRequest.getIsVote());
			voteToSave.setArea(areaBDD);
			voteToSave.setEmployee(employeeBDD);
			voteToSave.setDateCreate(new Date());
			voteToSave.setUserCreate(voteRequest.getUserCreate());
			vote = voteService.save(voteToSave);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to save");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Saved");
		response.put("area", vote);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
