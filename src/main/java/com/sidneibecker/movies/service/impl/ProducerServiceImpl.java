package com.sidneibecker.movies.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sidneibecker.movies.dto.MinMaxProducerIntervalDTO;
import com.sidneibecker.movies.dto.ProducerIntervalDTO;
import com.sidneibecker.movies.model.Producer;
import com.sidneibecker.movies.repository.ProducerRepository;
import com.sidneibecker.movies.repository.ProducerRepositoryCustom;
import com.sidneibecker.movies.service.ProducerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {

	@Autowired
	ProducerRepositoryCustom producerRepositoryCustom;

	@Autowired
	ProducerRepository producerRepository;

	@Override
	public List<Producer> getAll() {
		return producerRepository.findAll();
	}

	@Override
	public MinMaxProducerIntervalDTO getMinMaxInterval() {

		// Get the list of producers with interval of wins
		List<ProducerIntervalDTO> producerIntervalDTOs = producerRepositoryCustom.getSummary();

		// Get the min and max interval
		Long minInterval = producerIntervalDTOs.stream().map(ProducerIntervalDTO::getInterval).min(Long::compare).orElse(0L);
		Long maxInterval = producerIntervalDTOs.stream().map(ProducerIntervalDTO::getInterval).max(Long::compare).orElse(0L);

		MinMaxProducerIntervalDTO minMaxProducerIntervalDTO = new MinMaxProducerIntervalDTO();
		minMaxProducerIntervalDTO.setMin(this.getProducersWithWinInterval(producerIntervalDTOs, minInterval));
		minMaxProducerIntervalDTO.setMax(this.getProducersWithWinInterval(producerIntervalDTOs, maxInterval));

		return minMaxProducerIntervalDTO;
	}

	/**
	 * 
	 * @param producerIntervalDTOs
	 * @param interval
	 * @return List of producers that correspond to the interval of wins
	 */
	private List<ProducerIntervalDTO> getProducersWithWinInterval(List<ProducerIntervalDTO> producerIntervalDTOs, Long interval) {
		return producerIntervalDTOs.stream().filter(producer -> producer.getInterval().equals(interval)).collect(Collectors.toList());
	}
}
