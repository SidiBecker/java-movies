package com.sidneibecker.movies.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sidneibecker.movies.dto.MovieDTO;
import com.sidneibecker.movies.mapper.MovieMapper;
import com.sidneibecker.movies.model.Movie;

@Component
public class MovieMapperImpl implements MovieMapper {

	@Override
	public MovieDTO toDTO(Movie movie) {
		if (movie == null) {
			return null;
		}

		MovieDTO movieDTO = new MovieDTO();

		movieDTO.setId(movie.getId());
		movieDTO.setTitle(movie.getTitle());
		movieDTO.setWinner(movie.getWinner());
		movieDTO.setYear(movie.getYear());

		return movieDTO;
	}

	@Override
	public List<MovieDTO> toDTOs(List<Movie> movies) {
		if (movies == null) {
			return null;
		}

		List<MovieDTO> list = new ArrayList<MovieDTO>(movies.size());
		for (Movie movie : movies) {
			list.add(toDTO(movie));
		}

		return list;
	}
}
