package com.avalith.votes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avalith.votes.model.Vote;
import com.avalith.votes.repository.VoteRepository;

@Service
@Transactional(readOnly = true)
public class VoteServiceImpl implements VoteService {

	@Autowired
	private VoteRepository voteRepository;

	@Override
	public List<Vote> findAll() {
		return voteRepository.findAll();
	}

	@Override
	public Vote findById(String id) {
		return voteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Vote save(Vote vote) {
		return voteRepository.save(vote);
	}

	@Override
	@Transactional
	public void delete(String id) {
		voteRepository.deleteById(id);
	}
}
