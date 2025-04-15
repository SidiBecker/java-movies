package com.sidneibecker.movies.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sidneibecker.movies.dto.MinMaxProducerIntervalDTO;

@RestController
@RequestMapping("/producers")
public interface ProducerController {

	@GetMapping("/min-max-interval")
	public MinMaxProducerIntervalDTO getMinMaxInterval();

}
