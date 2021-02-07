package com.example.movie.errors;

import java.io.Serializable;
import java.util.List;

public class ParameterizedErrorVM implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private final List<String> messages;
	private final boolean success;

	public ParameterizedErrorVM(String errorCode,List<String> messages) {
		this.errorCode = errorCode;
		this.messages = messages;
		this.success = false;
	}

	public List<String>  getMessages() {
		return messages;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public boolean isSuccess() {
		return success;
	}

}
