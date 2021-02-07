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
@Table(name = "review")
public class Review {
	

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	
	
	@Column(name = "movie_score")
	private Long movieScore;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "movie_id")
	private Long movieId;
	
	@Column(name = "scoredBy")
	private String scoredBy;
	
	@Column(name = "genre")
	private String genre;
	
	
	

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getScoredBy() {
		return scoredBy;
	}

	public void setScoredBy(String scoredBy) {
		this.scoredBy = scoredBy;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Long getMovieScore() {
		return movieScore;
	}

	public void setMovieScore(Long movieScore) {
		this.movieScore = movieScore;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	
	
	
	

}
