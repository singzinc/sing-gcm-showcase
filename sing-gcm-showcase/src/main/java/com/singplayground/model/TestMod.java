package com.singplayground.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_mod")
public class TestMod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tid", unique = true, nullable = false, columnDefinition = "int")
	private long tid;

	@Column(name = "message")
	private String message;

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
