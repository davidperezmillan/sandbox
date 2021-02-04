package com.davidperezmillan.sandbox.rest.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "status")
	private boolean status;


}
