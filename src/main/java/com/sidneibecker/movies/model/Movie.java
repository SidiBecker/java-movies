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
import lombok.Data;

@Entity
@Data
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
}