package com.sidneibecker.movies.startup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidneibecker.movies.service.MovieService;

import jakarta.annotation.PostConstruct;

@Service
public class StartupService {

	@Autowired
	MovieService movieService;

	@PostConstruct
	public void onStartup() {
		movieService.saveCsvToDatabase();
	}
}