package com.avalith.votes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "areas")
public class Area implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	private String name;
	private String userCreate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreate;

	public Area() {

	}

	public Area(String name, String userCreate) {
		this.name = name;
		this.userCreate = userCreate;
		this.dateCreate = new Date();
	}
}
