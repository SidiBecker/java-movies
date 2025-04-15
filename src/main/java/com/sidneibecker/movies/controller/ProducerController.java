package com.sidneibecker.movies.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sidneibecker.movies.dto.MinMaxProducerIntervalDTO;
import com.sidneibecker.movies.model.Producer;

@RestController
@RequestMapping("/producers")
public interface ProducerController {

	@GetMapping()
	public List<Producer> getAll();

	@GetMapping("/min-max-interval")
	public MinMaxProducerIntervalDTO getMinMaxInterval();

}
