package com.sql.uri2611;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sql.uri2611.dto.MovieMinDTO;
import com.sql.uri2611.projections.MovieMinProjection;
import com.sql.uri2611.repositories.MovieRepository;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner{
	
	@Autowired
	private MovieRepository movieRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<MovieMinProjection> movies = movieRepository.searchSQL("Action");
		List<MovieMinDTO> moviesDto = movies.stream()
				.map(movie -> new MovieMinDTO(movie)).collect(Collectors.toList());
		
		System.out.println("\n******  ROOT SQL RESULT  ******** \n");
	
		for (MovieMinDTO movie : moviesDto) {
			System.out.println(movie);
		}
		
		System.out.println("\n\n");
		
		List<MovieMinDTO> moviesJPQL = movieRepository.searchJPQL("Action");
		
		System.out.println("\n******  ROOT JPQL RESULT  ******** \n");
		
		for (MovieMinDTO movie : moviesJPQL) {
			System.out.println(movie);
		}
		
	}
}











