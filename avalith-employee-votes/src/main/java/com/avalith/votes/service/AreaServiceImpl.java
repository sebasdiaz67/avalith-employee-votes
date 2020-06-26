package com.avalith.votes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avalith.votes.model.Area;
import com.avalith.votes.repository.AreaRepository;

@Service
@Transactional(readOnly = true)
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaRepository areaRepository;

	@Override
	public List<Area> findAll() {
		return areaRepository.findAll();
	}

	@Override
	public Area findById(String id) {
		return areaRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Area save(Area area) {
		return areaRepository.save(area);
	}

	@Override
	@Transactional
	public void delete(String id) {
		areaRepository.deleteById(id);
	}

}
