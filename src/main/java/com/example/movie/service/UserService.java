package com.example.movie.service;

import java.util.List;

import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;
import com.example.movie.entity.User;
import com.example.movie.model.MovieModel;
import com.example.movie.model.ReviewModel;
import com.example.movie.model.ReviewTopModel;
import com.example.movie.model.UserModel;

public interface UserService {

	Long saveOrUpdate(UserModel person);

	List<User> getAllUsers();

	Long saveMovies(MovieModel movie);

	List<Movie> getAllMovies();

	String reviewMovies(Long id, ReviewModel movie);

	List<Review> reviewtopMovies(ReviewTopModel movie);

	Integer reviewtopRelease(String movie);

	Integer reviewtopYearRelease(String year);

}
