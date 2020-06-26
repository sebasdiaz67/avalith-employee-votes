package com.avalith.votes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avalith.votes.model.Area;

public interface AreaRepository extends JpaRepository<Area, String> {

}
