package com.sidneibecker.movies.repository.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.sidneibecker.movies.dto.ProducerIntervalDTO;
import com.sidneibecker.movies.repository.ProducerRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProducerRepositoryImpl implements ProducerRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<ProducerIntervalDTO> getSummary() {

		StringBuilder sql = new StringBuilder();
		sql.append("WITH intervals AS (                                                                                     ");
		sql.append("  SELECT                                                                                                ");
		sql.append("    p.tx_name AS producer,                                                                              ");
		sql.append("    m.nr_year AS current_year,                                                                          ");
		sql.append("    LAG(m.nr_year) OVER (PARTITION BY p.id_producer ORDER BY m.nr_year) AS previous_year,               ");
		sql.append("    (m.nr_year - LAG(m.nr_year) OVER (PARTITION BY p.id_producer ORDER BY m.nr_year)) AS interval_years ");
		sql.append("  FROM                                                                                                  ");
		sql.append("    tb_movieproducer mp                                                                                 ");
		sql.append("  JOIN tb_producer p ON mp.id_producer = p.id_producer                                                  ");
		sql.append("  JOIN tb_movie m ON mp.id_movie = m.id_movie                                                           ");
		sql.append("  WHERE                                                                                                 ");
		sql.append("    m.fl_winner = true                                                                                  ");
		sql.append(")                                                                                                       ");
		sql.append("SELECT *                                                                                                ");
		sql.append("FROM intervals                                                                                          ");
		sql.append("WHERE interval_years IS NOT NULL                                                                        ");
		sql.append("ORDER BY interval_years DESC;                                                                           ");

		List<Object[]> resultados = entityManager.createNativeQuery(sql.toString()).getResultList();

		return resultados.stream().map(obj -> {

			String name = (String) obj[0];
			Long currentYear = (Long) obj[1];
			Long previousYear = (Long) obj[2];
			Long interval = (Long) obj[3];

			return new ProducerIntervalDTO(name, interval, previousYear, currentYear);

		}).collect(Collectors.toList());
	}
}