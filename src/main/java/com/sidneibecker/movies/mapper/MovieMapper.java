package com.sidneibecker.movies.mapper;

import java.util.List;

import com.sidneibecker.movies.dto.MovieDTO;
import com.sidneibecker.movies.model.Movie;

public interface MovieMapper {

	MovieDTO toDTO(Movie movie);

	List<MovieDTO> toDTOs(List<Movie> movies);
}
