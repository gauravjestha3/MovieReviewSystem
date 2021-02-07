package com.example.movie.model;

import javax.validation.constraints.NotEmpty;

public class ReviewTopModel {

	@NotEmpty(message="Please provide genre")
	private String genre;
	@NotEmpty(message="Please provide type")
	private String type;
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
