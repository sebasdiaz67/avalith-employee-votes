package com.avalith.votes.controller.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class VoteVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean isVote;
	private String description;
	private String idArea;
	private String idEmployee;
	private String userCreate;
}
