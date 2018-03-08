package com.qmh.sle.bean;

import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LuckMsg implements Serializable {
	
	@JsonProperty("message")
	private String _message;

	public String getMessage() {
		return _message;
	}

	public void setMessage(String message) {
		this._message = message;
	}
}
