package com.avalith.votes.service;

import java.util.List;

import com.avalith.votes.model.Area;

public interface AreaService {

	public List<Area> findAll();

	public Area findById(String id);

	public Area save(Area area);

	public void delete(String id);
}
