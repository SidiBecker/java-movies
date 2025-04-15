package com.sidneibecker.movies.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.sidneibecker.movies.dto.MovieDTO;
import com.sidneibecker.movies.model.Movie;

@Mapper(componentModel = "spring")
public interface MovieMapper {
	MovieDTO toDTO(Movie movie);
	
	List<MovieDTO> toDTOs(List<Movie> movies);
}
