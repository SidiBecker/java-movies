package com.sidneibecker.movies.service;

import java.util.List;

import com.sidneibecker.movies.dto.MinMaxProducerIntervalDTO;
import com.sidneibecker.movies.model.Producer;

public interface ProducerService {

	public List<Producer> getAll();

	public MinMaxProducerIntervalDTO getMinMaxInterval();

}
