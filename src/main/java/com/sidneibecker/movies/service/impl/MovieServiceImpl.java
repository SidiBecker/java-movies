package com.sidneibecker.movies.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidneibecker.movies.model.Movie;
import com.sidneibecker.movies.repository.MovieRepository;
import com.sidneibecker.movies.service.MovieService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	@PostConstruct
	public void init() {
		this.saveCsvToDatabase();
	}

	private void saveCsvToDatabase() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/movies.csv")))) {

			reader.readLine(); // Skip header

			reader.lines().forEach(line -> {

				// Split delimiters
				String[] fields = line.split(";");

				// Create a new movie model
				Movie movie = new Movie();
				movie.setReleaseYear(Long.valueOf(fields[0]));
				movie.setTitle(fields[1]);
				movie.setStudios(fields[2]);
				movie.setProducers(fields[3]);

				// When the movie is not a winner, the field is empty
				if (fields.length == 5) {
					movie.setWinner(fields[4].trim().equalsIgnoreCase("yes"));
				} else {
					movie.setWinner(Boolean.FALSE);
				}

				// Save to database
				movieRepository.save(movie);
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
}
