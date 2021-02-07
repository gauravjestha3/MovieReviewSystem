package com.example.movie.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.movie.entity.Movie;
import com.example.movie.entity.Review;
import com.example.movie.entity.User;
import com.example.movie.errors.CustomExceptionCode;
import com.example.movie.errors.CustomParameterizedException;
import com.example.movie.model.MovieModel;
import com.example.movie.model.ReviewModel;
import com.example.movie.model.ReviewTopModel;
import com.example.movie.model.UserModel;
import com.example.movie.repo.MovieRepo;
import com.example.movie.repo.ReviewRepo;
import com.example.movie.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private MovieRepo movieRepo;
	
	@Autowired
	private ReviewRepo reviewRepo;
	
	

	@SuppressWarnings("deprecation")
	@Override
	public Long saveOrUpdate(UserModel person) {
		if(Objects.isNull(person)) {
			throw new CustomParameterizedException(CustomExceptionCode.USER_NOT_FOUND.getErrCode(),
					CustomExceptionCode.USER_NOT_FOUND.getErrMsg(),
					CustomExceptionCode.USER_NOT_FOUND.getDescription());
		}
		if(StringUtils.isEmpty(person.getName())) {
			throw new CustomParameterizedException(CustomExceptionCode.USER_NAME_NOT_FOUND.getErrCode(),
					CustomExceptionCode.USER_NAME_NOT_FOUND.getErrMsg(),
					CustomExceptionCode.USER_NAME_NOT_FOUND.getDescription());
		}
		User user=new User();
		user.setUserName(person.getName());
		user.setUserType("VIEWER");
		user.setReviewCount(0);
		userRepo.save(user);
		
		return user.getID();
	}




	@Override
	public List<User> getAllUsers() {
		List<User>user=userRepo.findAll();
		if(Objects.isNull(user)) {
			throw new CustomParameterizedException(CustomExceptionCode.NO_DATA_FOUND.getErrCode(),
					CustomExceptionCode.NO_DATA_FOUND.getErrMsg(),
					CustomExceptionCode.NO_DATA_FOUND.getDescription());
		}
		return user;
	}




	@Override
	public Long saveMovies(MovieModel newmovie) {
		if(Objects.isNull(newmovie)) {
			throw new CustomParameterizedException(CustomExceptionCode.USER_NOT_FOUND.getErrCode(),
					CustomExceptionCode.USER_NOT_FOUND.getErrMsg(),
					CustomExceptionCode.USER_NOT_FOUND.getDescription());
		}
		
		Movie movie=new Movie();
		movie.setMovieName(newmovie.getMovieName());
		movie.setMovieStatus(newmovie.getStatus());
		movie.setGenre(newmovie.getGenre());
		String myDate = (newmovie.getReleaseYear());
		movie.setReleaseYear(myDate);
		movieRepo.save(movie);
		return movie.getID();
		
	}




	@Override
	public List<Movie> getAllMovies() {
		List<Movie>moviesName=movieRepo.findAll();
		if(Objects.isNull(moviesName)) {
			throw new CustomParameterizedException(CustomExceptionCode.NO_DATA_FOUND.getErrCode(),
					CustomExceptionCode.NO_DATA_FOUND.getErrMsg(),
					CustomExceptionCode.NO_DATA_FOUND.getDescription());
		}
		return moviesName;
	}




	@Override
	public String reviewMovies(Long id, ReviewModel movie) {
		String success="Successfully Reviewed";
		Optional<User> user=userRepo.findById(id);
		if(!user.isPresent()) {
			throw new CustomParameterizedException(CustomExceptionCode.NO_DATA_FOUND.getErrCode(),
					CustomExceptionCode.NO_DATA_FOUND.getErrMsg(),
					CustomExceptionCode.NO_DATA_FOUND.getDescription());
		}
		
		
		Optional<Movie> movies=movieRepo.findById(movie.getMovieId());
		
		if(!movies.isPresent()) {
			throw new CustomParameterizedException(CustomExceptionCode.NO_DATA_FOUND.getErrCode(),
					CustomExceptionCode.NO_DATA_FOUND.getErrMsg(),
					CustomExceptionCode.NO_DATA_FOUND.getDescription());
		}
		if(movies.get().getMovieStatus().equals("Upcoming")) {
			throw new CustomParameterizedException(CustomExceptionCode.MOVIE_NOT_RELEASED.getErrCode(),
					CustomExceptionCode.MOVIE_NOT_RELEASED.getErrMsg(),
					CustomExceptionCode.MOVIE_NOT_RELEASED.getDescription());
		}
		if(user.get().getReviewCount()==3) {
			user.get().setUserType("Critics");
			userRepo.save(user.get());
		}
		Review reviewsss=reviewRepo.findByMovieIdAndUserId(movie.getMovieId(),id);
		if(Objects.nonNull(reviewsss)) {
			throw new CustomParameterizedException(CustomExceptionCode.ALREADY_REVIEWED.getErrCode(),
					CustomExceptionCode.ALREADY_REVIEWED.getErrMsg(),
					CustomExceptionCode.ALREADY_REVIEWED.getDescription());
			
		}
		if(user.get().getReviewCount()>=3) {
		if(user.get().getUserType().equalsIgnoreCase("Critics")) {
			Review review=new Review();
			review.setMovieId(movie.getMovieId());
			review.setUserId(id);
			review.setMovieScore(2*movie.getMovieScore());
			review.setScoredBy("Critics");
			review.setGenre(movies.get().getGenre());
			reviewRepo.save(review);
			int count=user.get().getReviewCount();
			user.get().setReviewCount(count+1);
			userRepo.save(user.get());
			
			
		}
		}
		else {
			Review review=new Review();
			review.setMovieId(movie.getMovieId());
			review.setUserId(id);
			review.setMovieScore(movie.getMovieScore());
			review.setScoredBy("Viewer");
			review.setGenre(movies.get().getGenre());
			reviewRepo.save(review);
			
			int count=user.get().getReviewCount();
			user.get().setReviewCount(count+1);
			userRepo.save(user.get());
			
		}
		
		return success;
	}




	@Override
	public List<Review> reviewtopMovies(ReviewTopModel movie) {
		if(Objects.isNull(movie)) {
			throw new CustomParameterizedException(CustomExceptionCode.NO_DATA_FOUND.getErrCode(),
					CustomExceptionCode.NO_DATA_FOUND.getErrMsg(),
					CustomExceptionCode.NO_DATA_FOUND.getDescription());
		}
		List<Review>reviews=reviewRepo.findByScoredByAndGenreDesc(movie.getGenre(),movie.getType());
		if(Objects.isNull(reviews)) {
			throw new CustomParameterizedException(CustomExceptionCode.NO_DATA_FOUND.getErrCode(),
					CustomExceptionCode.NO_DATA_FOUND.getErrMsg(),
					CustomExceptionCode.NO_DATA_FOUND.getDescription());
		}
		return reviews;
	}




	@Override
	public Integer reviewtopRelease(String movie) {
		if(Objects.isNull(movie)) {
			throw new CustomParameterizedException(CustomExceptionCode.NO_DATA_FOUND.getErrCode(),
					CustomExceptionCode.NO_DATA_FOUND.getErrMsg(),
					CustomExceptionCode.NO_DATA_FOUND.getDescription());
		}
		Movie movies =movieRepo.findByMovieName(movie);
		if(StringUtils.isEmpty(movies)) {
			throw new CustomParameterizedException(CustomExceptionCode.NO_DATA_FOUND.getErrCode(),
					CustomExceptionCode.NO_DATA_FOUND.getErrMsg(),
					CustomExceptionCode.NO_DATA_FOUND.getDescription());
		}
		List<Review>list=reviewRepo.findByMovieId(movies.getID());
		if(list.isEmpty()) {
			throw new CustomParameterizedException(CustomExceptionCode.NO_DATA_FOUND.getErrCode(),
					CustomExceptionCode.NO_DATA_FOUND.getErrMsg(),
					CustomExceptionCode.NO_DATA_FOUND.getDescription());
			
		}
		Integer count=list.size();
		Long add=0l;
		for(Review id:list) {
			Long counts=id.getMovieScore();
			add=add+counts;
		}
		Integer adds=add.intValue();
		Integer score=adds/count;
		return score;
	}




	@Override
	public Integer reviewtopYearRelease(String year) {
		if(Objects.isNull(year)) {
			throw new CustomParameterizedException(CustomExceptionCode.NO_DATA_FOUND.getErrCode(),
					CustomExceptionCode.NO_DATA_FOUND.getErrMsg(),
					CustomExceptionCode.NO_DATA_FOUND.getDescription());
		}
		List<Movie> movies =movieRepo.findByMovieYear(year);
		if(movies.isEmpty()) {
			throw new CustomParameterizedException(CustomExceptionCode.NO_DATA_FOUND.getErrCode(),
					CustomExceptionCode.NO_DATA_FOUND.getErrMsg(),
					CustomExceptionCode.NO_DATA_FOUND.getDescription());
		}
		Integer count=movies.size();
		Long add=0l;
		for(Movie id:movies) {
			Optional<Review> revi=reviewRepo.findById(id.getID());
			Long counts=revi.get().getMovieScore();
			add=add+counts;
		}
		Integer adds=add.intValue();
		Integer score=adds/count;
		return score;
	}




	
	






}
