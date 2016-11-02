package com.singplayground.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "test_datetime")
public class TestDateTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, columnDefinition = "int")
	private long id;

	@Column(name = "test_datetime")
	private Date dateTime;

	@Column(name = "test_datetime2")
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
	//@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm")
	private Date dateTime2;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Date getDateTime2() {
		return dateTime2;
	}

	public void setDateTime2(Date dateTime2) {
		this.dateTime2 = dateTime2;
	}

}
