package com.example.movie.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.movie.entity.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Long> {
	@Query("SELECT m FROM Movie m WHERE m.movieName=:movie")
	Movie findByMovieName(@Param("movie") String movie);

	@Query("SELECT m FROM Movie m WHERE m.releaseYear=:year")
	List<Movie> findByMovieYear(@Param("year") String year);

}
