package com.sidneibecker.movies.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.sidneibecker.movies.service.MovieService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;

	@Autowired
	ProducerRepository producerRepository;

	@Autowired
	StudioRepository studioRepository;

	@Autowired
	MovieMapper movieMapper;

	@Override
	public void saveCsvToDatabase() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/movies.csv")))) {

			reader.readLine(); // Skip header

			reader.lines().forEach(line -> {

				// Split delimiters
				String[] fields = line.split(";");

				// Create a new movie model
				Movie movie = new Movie();
				movie.setYear(Long.valueOf(fields[0]));
				movie.setTitle(fields[1]);

				// Create Studios relation

				String studioNames = fields[2];

				List<MovieStudio> movieStudios = new ArrayList<>();

				for (String studioName : studioNames.split(",")) {

					// Search for an already saved studio record
					Studio studio = studioRepository.findByName(studioName).orElse(new Studio());

					// When not found, create a new one
					if (studio.getId() == null) {
						studio.setName(studioName);
						studio = studioRepository.save(studio);
					}

					// Link studio with movie
					MovieStudio movieStudio = new MovieStudio(null, movie, studio);

					movieStudios.add(movieStudio);
				}

				// Link all studios with movie
				movie.setMovieStudios(movieStudios);

				// Create Producers relations
				String producerNames = fields[3];

				List<MovieProducer> movieProducers = new ArrayList<>();

				for (String producerName : producerNames.split(",| and ")) {

					producerName = producerName.trim();

					if (producerName.length() == 0) {
						continue;
					}

					// Search for an already saved producer record
					Producer producer = producerRepository.findByName(producerName).orElse(new Producer());

					// When not found, create a new one
					if (producer.getId() == null) {
						producer.setName(producerName);
						producer = producerRepository.save(producer);
					}

					// Link producer with movie
					MovieProducer movieProducer = new MovieProducer(null, movie, producer);

					movieProducers.add(movieProducer);
				}

				// Link all producers with movie
				movie.setMovieProducers(movieProducers);

				// When the movie is not a winner, the field is empty in .csv file
				if (fields.length == 5) {
					movie.setWinner(fields[4].trim().equalsIgnoreCase("yes"));
				} else {
					movie.setWinner(Boolean.FALSE);
				}

				movieRepository.save(movie);
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MovieDTO> getAll() {

		List<Movie> movies = movieRepository.findAll();

		List<MovieDTO> movieDTOs = new ArrayList<>();

		for (Movie movie : movies) {

			MovieDTO movieDTO = movieMapper.toDTO(movie);
			movieDTO.setProducers(movie.getMovieProducers().stream().map(x -> x.getProducer().getName()).collect(Collectors.toList()));
			movieDTO.setStudios(movie.getMovieStudios().stream().map(x -> x.getStudio().getName()).collect(Collectors.toList()));

			movieDTOs.add(movieDTO);
		}

		return movieDTOs;
	}

}
