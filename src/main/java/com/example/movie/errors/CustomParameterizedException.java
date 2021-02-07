package com.example.movie.errors;

import java.util.ArrayList;
import java.util.List;


public class CustomParameterizedException  extends RuntimeException{

	
	private static final long serialVersionUID = 1L;

	private final String errorCode;
	private List<String> errorMessages = new ArrayList<String>();
	private final List<String> paramList = new ArrayList<String>();

	public CustomParameterizedException(String errorCode,String message, String... errMsg) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorMessages.add(message);
		if (errMsg != null && errMsg.length > 0) {
			for (int i = 0; i < errMsg.length; i++) {
				errorMessages.add(errMsg[i]);
			}
		}
	}

	public CustomParameterizedException(String errorCode,List<String> errorMessages, List<String> paramList) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorMessages.addAll(errorMessages);
		this.paramList.addAll(paramList);
	}

	public ParameterizedErrorVM getErrorVM() {
		return new ParameterizedErrorVM(errorCode,errorMessages);
	}
}
