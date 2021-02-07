package com.example.movie.model;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

public class MovieModel {
	@NotEmpty(message="Please provide movie Name")
	private String movieName;
	@NotEmpty(message="Please provide movie status")
	private String status;
	
	@NotEmpty(message="Please provide movie year")
	private String releaseYear;
	
	@NotEmpty(message="Please provide movie genre")
	private String genre;
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
	
	

}
