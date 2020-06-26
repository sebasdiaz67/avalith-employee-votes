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

import com.avalith.votes.controller.vo.AreaVO;
import com.avalith.votes.model.Area;
import com.avalith.votes.service.AreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = "area")
public class AreaRestController {

	private final Logger log = LoggerFactory.getLogger(AreaRestController.class);

	@Autowired
	private AreaService areaService;
	
	@GetMapping("/areas")
	@ApiOperation(value = "Find all", notes = "Service to find all areas")
	public List<Area> findAll() {
		log.info("muestra todas las areas");
		return areaService.findAll();
	}
	
	@GetMapping("/areas/{id}")
	@ApiOperation(value = "Find by id", notes = "Service to find the area by id")
	public ResponseEntity<?> findById(@PathVariable String id) {
		Area areaBDD = null;
		Map<String, Object> response = new HashMap<>();
		try {
			areaBDD = areaService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to get the data");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (areaBDD == null) {
			response.put("mensaje", "Area not found ".concat(" ID: ").concat(id));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(areaBDD, HttpStatus.OK);
	}
	
	@PostMapping("/areas")
	@ApiOperation(value = "Create", notes = "Service to save the area")
	public ResponseEntity<?> create(@RequestBody AreaVO areaRequest) {
		Area area = null;
		Map<String, Object> response = new HashMap<>();
		try {
			Area areaToSave = new Area();
			areaToSave.setName(areaRequest.getName());
			areaToSave.setDateCreate(new Date());
			areaToSave.setUserCreate(areaRequest.getUserCreate());
			area = areaService.save(areaToSave);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error to save");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "Saved");
		response.put("area", area);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
