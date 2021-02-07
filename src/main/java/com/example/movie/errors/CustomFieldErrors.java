package com.example.movie.errors;

public class CustomFieldErrors {

	private String fieldName;
	private String message;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CustomFieldErrors [fieldName=" + fieldName + ", message=" + message + "]";
	}

}
