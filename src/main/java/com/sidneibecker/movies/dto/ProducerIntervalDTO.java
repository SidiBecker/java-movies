package com.sidneibecker.movies.dto;

import lombok.Data;

@Data
public class ProducerIntervalDTO {
	private String producer;
	private Long interval;
	private Long previousWin;
	private Long followingWin;

	public ProducerIntervalDTO() {
	}

	public ProducerIntervalDTO(String producer, Long interval, Long previousWin, Long followingWin) {
		super();
		this.producer = producer;
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

}
