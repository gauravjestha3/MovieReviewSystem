package com.example.movie.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;
import com.example.movie.entity.User;
import com.example.movie.model.MovieModel;
import com.example.movie.model.ReviewModel;
import com.example.movie.model.ReviewTopModel;
import com.example.movie.model.UserModel;
import com.example.movie.service.UserService;
@RestController
@RequestMapping()
public class MovieController {
	
	
	private final Logger log = LoggerFactory.getLogger(MovieController.class);
	
	private final UserService userService;
	
	public MovieController(UserService userService) {
		this.userService=userService;
	}
	
	
	@PostMapping("/users")
    private Long saveUsers(@RequestBody UserModel person) {
		Long id=userService.saveOrUpdate(person);
        return id;
    }
	
	@GetMapping("/users")
	 public List<User> getAllUsers() {
	        List<User> persons = userService.getAllUsers();
	        
	        return persons;
	    }
	
	
	@PostMapping("/movies")
    private Long saveMovies(@RequestBody MovieModel movie) {
		Long id=userService.saveMovies(movie);
        return id;
    }
	
	
	@GetMapping("/movies")
	 public List<Movie> getAllMovies() {
	        List<Movie> movies = userService.getAllMovies();
	        
	        return movies;
	    }
	

	@PostMapping("/review")
    private String reviewMovies(@RequestHeader(name = "userId", required = true) Long id, @RequestBody ReviewModel movie) {
		String result=userService.reviewMovies(id,movie);
        return result;
    }
	
	@PostMapping("/review/top")
    private List<Review> reviewtopMovies( @RequestBody ReviewTopModel movie) {
		List<Review> result=userService.reviewtopMovies(movie);
        return result;
    }
	
	@GetMapping("/review/relase")
    private Integer reviewtopRelease( @RequestParam("movie") String movie) {
		Integer result=userService.reviewtopRelease(movie);
        return result;
    }
	
	@GetMapping("/review/year")
    private Integer reviewtopYearRelease(@RequestParam("year") String year) {
		Integer result=userService.reviewtopYearRelease(year);
        return result;
    }

}
