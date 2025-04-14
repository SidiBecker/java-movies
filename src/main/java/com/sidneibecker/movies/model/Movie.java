package com.sidneibecker.movies.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long id;

	private Long releaseYear;

	private String title;

	private String studios;

	private String producers;

	private Boolean winner;
}