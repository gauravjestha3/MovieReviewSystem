package com.example.movie.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long>{

	@Query("SELECT r FROM Review r WHERE r.genre=:genre AND r.scoredBy=:type ORDER BY movie_score DESC")
	List<Review> findByScoredByAndGenreDesc(@Param("genre") String genre,@Param("type") String type);

	@Query("SELECT r FROM Review r WHERE r.movieId=:movieId AND r.userId=:userId ")
	Review findByMovieIdAndUserId(@Param("movieId") Long movieId,@Param("userId") Long userId);

	@Query("SELECT r FROM Review r WHERE r.movieId=:movieId ")
	List<Review> findByMovieId(@Param("movieId") Long movieId);
	
	 

}
