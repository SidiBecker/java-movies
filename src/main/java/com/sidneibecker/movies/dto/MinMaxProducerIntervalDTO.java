package com.sidneibecker.movies.dto;

import java.util.List;

import lombok.Data;

@Data
public class MinMaxProducerIntervalDTO {

	private List<ProducerIntervalDTO> min;

	private List<ProducerIntervalDTO> max;
}
