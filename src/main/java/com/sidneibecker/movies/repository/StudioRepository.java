package com.sidneibecker.movies.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sidneibecker.movies.model.Studio;

@Repository
public interface StudioRepository extends JpaRepository<Studio, Long> {
	Optional<Studio> findByName(String name);
}