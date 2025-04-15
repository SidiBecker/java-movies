package com.sidneibecker.movies.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sidneibecker.movies.dto.MinMaxProducerIntervalDTO;
import com.sidneibecker.movies.dto.ProducerIntervalDTO;
import com.sidneibecker.movies.repository.ProducerRepository;
import com.sidneibecker.movies.repository.ProducerRepositoryCustom;
import com.sidneibecker.movies.service.impl.ProducerServiceImpl;

public class ProducerServiceImplTest {

	@Mock
	private ProducerRepositoryCustom producerRepositoryCustom;

	@Mock
	private ProducerRepository producerRepository;

	@InjectMocks
	private ProducerServiceImpl producerService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetMinMaxInterval() {

		ProducerIntervalDTO producerA = new ProducerIntervalDTO();
		producerA.setProducer("Producer A");
		producerA.setPreviousWin(1980L);
		producerA.setFollowingWin(1990L);
		producerA.setInterval(10L);

		ProducerIntervalDTO producerB = new ProducerIntervalDTO();
		producerB.setProducer("Producer B");
		producerB.setPreviousWin(1995L);
		producerB.setFollowingWin(2005L);
		producerB.setInterval(10L);

		ProducerIntervalDTO producerC = new ProducerIntervalDTO();
		producerC.setProducer("Producer C");
		producerC.setPreviousWin(2000L);
		producerC.setFollowingWin(2001L);
		producerC.setInterval(1L);

		List<ProducerIntervalDTO> fakeProducers = new ArrayList<>();
		fakeProducers.add(producerA);
		fakeProducers.add(producerB);
		fakeProducers.add(producerC);

		when(producerRepositoryCustom.getSummary()).thenReturn(fakeProducers);

		MinMaxProducerIntervalDTO result = producerService.getMinMaxInterval();

		assertThat(result).isNotNull();
		assertThat(result.getMin()).hasSize(1);
		assertThat(result.getMin().get(0).getProducer()).isEqualTo("Producer C");
		assertThat(result.getMin().get(0).getInterval()).isEqualTo(1L);

		assertThat(result.getMax()).hasSize(2);
		assertThat(result.getMax().get(0).getInterval()).isEqualTo(10L);
		assertThat(result.getMax().get(1).getInterval()).isEqualTo(10L);
	}
}
