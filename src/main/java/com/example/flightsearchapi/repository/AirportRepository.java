package com.example.flightsearchapi.repository;

import com.example.flightsearchapi.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
