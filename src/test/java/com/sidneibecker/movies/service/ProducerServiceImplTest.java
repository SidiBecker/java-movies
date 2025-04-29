package com.sidneibecker.movies.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.sidneibecker.movies.dto.MinMaxProducerIntervalDTO;
import com.sidneibecker.movies.dto.ProducerIntervalDTO;
import com.sidneibecker.movies.service.impl.ProducerServiceImpl;

import jakarta.inject.Inject;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProducerServiceImplTest {

	@Inject
	private ProducerServiceImpl producerService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetMinMaxInterval() {

		// Get the final response based on the .csv file
		MinMaxProducerIntervalDTO result = producerService.getMinMaxInterval();

		assertThat(result).isNotNull();
		assertThat(result.getMin()).hasSize(1);
		assertThat(result.getMax()).hasSize(1);

		ProducerIntervalDTO minInterval = result.getMin().get(0);

		assertThat(minInterval.getProducer()).isEqualTo("Joel Silver");
		assertThat(minInterval.getInterval()).isEqualTo(1L);
		assertThat(minInterval.getPreviousWin()).isEqualTo(1990L);
		assertThat(minInterval.getFollowingWin()).isEqualTo(1991L);

		ProducerIntervalDTO maxInterval = result.getMax().get(0);

		assertThat(maxInterval.getProducer()).isEqualTo("Matthew Vaughn");
		assertThat(maxInterval.getInterval()).isEqualTo(13L);
		assertThat(maxInterval.getPreviousWin()).isEqualTo(2002L);
		assertThat(maxInterval.getFollowingWin()).isEqualTo(2015L);
	}
}
