package com.example.movie.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ReviewModel {
	@NotEmpty(message="Please provide movie Id")
	private Long movieId;
	@NotEmpty(message="Please provide movie Score")
	@Min(1)
	@Max(10)
	private Long movieScore;
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public Long getMovieScore() {
		return movieScore;
	}
	public void setMovieScore(Long movieScore) {
		this.movieScore = movieScore;
	}
	
	
	

}
