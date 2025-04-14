package com.sidneibecker.movies.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	private Long releaseYear;

	private String title;

	private Boolean winner;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<MovieProducer> movieProducers;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<MovieStudio> movieStudios;
}