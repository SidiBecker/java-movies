package com.sidneibecker.movies.dto;

import java.util.List;

import lombok.Data;

@Data
public class MovieDTO {

	private Long id;

	private Long year;

	private String title;

	private Boolean winner;

	private List<String> producers;

	private List<String> studios;
}
