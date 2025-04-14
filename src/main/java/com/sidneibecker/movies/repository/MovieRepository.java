package com.sidneibecker.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sidneibecker.movies.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}