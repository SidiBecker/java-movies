package com.sidneibecker.movies.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sidneibecker.movies.model.Movie;

@RestController
@RequestMapping("/movies")
public interface MovieController {

	@GetMapping({ "", "/" })
	public List<Movie> getAll();

}
