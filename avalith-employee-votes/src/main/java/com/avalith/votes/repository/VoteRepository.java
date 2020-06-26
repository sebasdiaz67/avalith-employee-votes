package com.avalith.votes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avalith.votes.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, String> {

}
