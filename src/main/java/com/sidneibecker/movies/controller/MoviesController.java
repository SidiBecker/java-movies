package com.sidneibecker.movies.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviesController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}
