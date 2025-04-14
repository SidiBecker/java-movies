package com.sidneibecker.movies.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sidneibecker.movies.controller.MovieController;
import com.sidneibecker.movies.model.Movie;
import com.sidneibecker.movies.service.MovieService;

@RestController
public class MovieControllerImpl implements MovieController {

	@Autowired
	MovieService movieService;

	@Override
	public List<Movie> getAll() {
		return movieService.getAllMovies();
	}

}
