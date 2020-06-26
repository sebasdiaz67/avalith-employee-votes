package com.avalith.votes.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "votes")
public class Vote implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;
	private Boolean isVote;
	private String description;
	@OneToOne(cascade = CascadeType.ALL)
	private Area area;
	@OneToOne(cascade = CascadeType.ALL)
	private Employee employee;
	private String userCreate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreate;
}
