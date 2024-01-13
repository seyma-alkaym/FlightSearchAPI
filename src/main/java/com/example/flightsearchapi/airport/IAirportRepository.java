package com.example.flightsearchapi.airport;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IAirportRepository extends JpaRepository<Airport, Long> {
}
