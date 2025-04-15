package com.sidneibecker.movies.repository;

import java.util.List;

import com.sidneibecker.movies.dto.ProducerIntervalDTO;

public interface ProducerRepositoryCustom {

	List<ProducerIntervalDTO> getSummary();
}