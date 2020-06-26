package com.avalith.votes.service;

import java.util.List;

import com.avalith.votes.model.Vote;

public interface VoteService {

	public List<Vote> findAll();

	public Vote findById(String id);

	public Vote save(Vote vote);

	public void delete(String id);
}
