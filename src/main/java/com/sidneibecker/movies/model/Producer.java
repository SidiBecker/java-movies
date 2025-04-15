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
@Table(name = "tb_producer")
public class Producer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producer")
	private Long id;

	@Column(name = "tx_name")
	private String name;

	@OneToMany(mappedBy = "producer", cascade = CascadeType.ALL)
	private List<MovieProducer> movieProducers;

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

	public List<MovieProducer> getMovieProducers() {
		return movieProducers;
	}

	public void setMovieProducers(List<MovieProducer> movieProducers) {
		this.movieProducers = movieProducers;
	}
	
	
}