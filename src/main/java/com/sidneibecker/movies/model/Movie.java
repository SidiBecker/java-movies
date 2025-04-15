package com.sidneibecker.movies.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id_movie")
	private Long id;

	@Column(name = "nr_year")
	private Long year;

	@Column(name = "tx_title")
	private String title;

	@Column(name = "fl_winner")
	private Boolean winner;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<MovieProducer> movieProducers;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<MovieStudio> movieStudios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getWinner() {
		return winner;
	}

	public void setWinner(Boolean winner) {
		this.winner = winner;
	}

	public List<MovieProducer> getMovieProducers() {
		return movieProducers;
	}

	public void setMovieProducers(List<MovieProducer> movieProducers) {
		this.movieProducers = movieProducers;
	}

	public List<MovieStudio> getMovieStudios() {
		return movieStudios;
	}

	public void setMovieStudios(List<MovieStudio> movieStudios) {
		this.movieStudios = movieStudios;
	}
	
	
}