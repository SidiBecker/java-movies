package com.sidneibecker.movies.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Studio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "studio", cascade = CascadeType.ALL)
	private List<MovieStudio> movieStudios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MovieStudio> getMovieStudios() {
		return movieStudios;
	}

	public void setMovieStudios(List<MovieStudio> movieStudios) {
		this.movieStudios = movieStudios;
	}

}