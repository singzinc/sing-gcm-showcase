package com.singplayground.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageDTO {
	@JsonProperty("data")
	private NotificationDTO notification;
	//private String to;
	@JsonProperty("registration_ids")
	private List<String> registrationIds;

	public NotificationDTO getNotification() {
		return notification;
	}

	public void setNotification(NotificationDTO notification) {
		this.notification = notification;
	}

	/*
		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}
	*/

	public List<String> getRegistrationIds() {
		return registrationIds;
	}

	public void setRegistrationIds(List<String> registrationIds) {
		this.registrationIds = registrationIds;
	}

}
