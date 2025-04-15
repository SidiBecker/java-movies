package com.sidneibecker.movies.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_movieproducer")
public class MovieProducer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movieproducer")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_movie")
	private Movie movie;

	@ManyToOne
	@JoinColumn(name = "id_producer")
	private Producer producer;

	public MovieProducer() {
		super();
	}

	public MovieProducer(Long id, Movie movie, Producer producer) {
		super();
		this.id = id;
		this.movie = movie;
		this.producer = producer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}
}