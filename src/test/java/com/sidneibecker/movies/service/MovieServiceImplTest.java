package com.sidneibecker.movies.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.sidneibecker.movies.dto.MovieDTO;
import com.sidneibecker.movies.mapper.MovieMapper;
import com.sidneibecker.movies.model.Movie;
import com.sidneibecker.movies.model.MovieProducer;
import com.sidneibecker.movies.model.MovieStudio;
import com.sidneibecker.movies.model.Producer;
import com.sidneibecker.movies.model.Studio;
import com.sidneibecker.movies.repository.MovieRepository;
import com.sidneibecker.movies.repository.ProducerRepository;
import com.sidneibecker.movies.repository.StudioRepository;
import com.sidneibecker.movies.service.impl.MovieServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MovieServiceImplTest {

	@Mock
	private MovieRepository movieRepository;

	@Mock
	private MovieMapper movieMapper;

	@InjectMocks
	private MovieServiceImpl movieService;

	@Mock
	private StudioRepository studioRepository;

	@Mock
	private ProducerRepository producerRepository;

	@Test
	void testSaveCsvToDatabase() throws Exception {

		StringBuilder csvContentBuilder = new StringBuilder();
		csvContentBuilder.append("year;title;studios;producers;winner\n");
		csvContentBuilder.append("1980;Movie 1;Studio A;Producer A and Producer B;yes\n");
		csvContentBuilder.append("1990;Movie 2;Studio B, Studio C;Producer C;\n");

		String csvContent = csvContentBuilder.toString();

		InputStream csvStream = new ByteArrayInputStream(csvContent.getBytes());

		// When searching for a studio, return none
		when(studioRepository.findByName(anyString())).thenReturn(Optional.empty());

		// Return itself with an id when save
		when(studioRepository.save(any(Studio.class))).thenAnswer(i -> {
			Studio s = i.getArgument(0);
			s.setId(new Random().nextLong());
			return s;
		});

		// When searching for a producer, return none
		when(producerRepository.findByName(anyString())).thenReturn(Optional.empty());

		// Return itself with an id when save
		when(producerRepository.save(any(Producer.class))).thenAnswer(i -> {
			Producer p = i.getArgument(0);
			p.setId(new Random().nextLong());
			return p;
		});

		// Return it self when save
		when(movieRepository.save(any(Movie.class))).thenAnswer(i -> i.getArgument(0));

		movieService.saveCsvToDatabase(csvStream);

		// Assert save two movies
		verify(movieRepository, times(2)).save(any(Movie.class));

		// Assert save at least one studio and one producer
		verify(studioRepository, atLeastOnce()).save(any(Studio.class));
		verify(producerRepository, atLeastOnce()).save(any(Producer.class));
	}

	@Test
	void testGetAll() {

		// Movie
		Movie movie1 = new Movie();
		movie1.setId(1L);
		movie1.setTitle("Movie 1");
		movie1.setYear(1990L);
		movie1.setWinner(true);

		// Producers
		Producer producer1 = new Producer();
		producer1.setName("Producer A");

		MovieProducer movieProducer1 = new MovieProducer();
		movieProducer1.setProducer(producer1);
		movie1.setMovieProducers(Arrays.asList(movieProducer1));

		// Studios
		Studio studio1 = new Studio();
		studio1.setName("Studio A");

		MovieStudio movieStudio1 = new MovieStudio();
		movieStudio1.setStudio(studio1);
		movie1.setMovieStudios(Arrays.asList(movieStudio1));

		List<Movie> movies = Arrays.asList(movie1);

		// Mock return of repository
		when(movieRepository.findAll()).thenReturn(movies);

		// Mock mapping
		MovieDTO movieDTO1 = new MovieDTO();
		movieDTO1.setId(1L);
		movieDTO1.setTitle("Movie 1");
		movieDTO1.setYear(1990L);
		movieDTO1.setWinner(true);

		when(movieMapper.toDTO(movie1)).thenReturn(movieDTO1);

		List<MovieDTO> result = movieService.getAll();

		assertNotNull(result);
		assertEquals(1, result.size());

		MovieDTO dto = result.get(0);
		assertEquals(1L, dto.getId());
		assertEquals("Movie 1", dto.getTitle());
		assertEquals(1990, dto.getYear());
		assertTrue(dto.getWinner());

		assertEquals(dto.getProducers().get(0), "Producer A");
		assertEquals(dto.getStudios().get(0), "Studio A");

		verify(movieRepository, times(movies.size())).findAll();
		verify(movieMapper, times(movies.size())).toDTO(movie1);
	}

}
