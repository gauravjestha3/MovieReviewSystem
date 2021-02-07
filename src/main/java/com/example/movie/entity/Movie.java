package com.example.movie.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "movie")
public class Movie {
	
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	@Column(name = "movie_name")
	private String movieName;
	
	@Column(name = "movie_status")
	private String movieStatus;
	
	@Column(name = "releaseYear")
	private String releaseYear;
	
	@Column(name = "genre")
	private String genre;
	
	
	
	


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getReleaseYear() {
		return releaseYear;
	}


	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Long getID() {
		return ID;
	}


	public void setID(Long iD) {
		ID = iD;
	}


	public String getMovieName() {
		return movieName;
	}


	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}


	public String getMovieStatus() {
		return movieStatus;
	}


	public void setMovieStatus(String movieStatus) {
		this.movieStatus = movieStatus;
	}


	
	
	


}
