package com.sidneibecker.movies.service;

import java.io.InputStream;
import java.util.List;

import com.sidneibecker.movies.dto.MovieDTO;

public interface MovieService {

	public List<MovieDTO> getAll();

	void saveCsvToDatabase(InputStream inputStream);

}
