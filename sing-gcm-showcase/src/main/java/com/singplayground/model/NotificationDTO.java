package com.singplayground.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationDTO {
	private String title;
	private String message;
	@JsonProperty("notId")
	private Long msgId;
	private String type;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
