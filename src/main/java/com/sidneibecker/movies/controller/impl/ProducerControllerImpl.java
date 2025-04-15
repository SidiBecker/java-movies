package com.sidneibecker.movies.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sidneibecker.movies.controller.ProducerController;
import com.sidneibecker.movies.dto.MinMaxProducerIntervalDTO;
import com.sidneibecker.movies.service.ProducerService;

@RestController
public class ProducerControllerImpl implements ProducerController {

	@Autowired
	ProducerService producerService;

	@Override
	public MinMaxProducerIntervalDTO getMinMaxInterval() {
		return producerService.getMinMaxInterval();
	}

}
