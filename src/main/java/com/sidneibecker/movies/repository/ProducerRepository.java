package com.sidneibecker.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sidneibecker.movies.model.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
	Optional<Producer> findByName(String name);
}