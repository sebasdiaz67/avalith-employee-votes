package com.avalith.votes.controller.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmployeeVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String firtsName;
	private String lastName;
	private String userName;
	private String password;
	private String userCreate;
}
