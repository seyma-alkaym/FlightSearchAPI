package com.example.flightsearchapi.repository;


import com.example.flightsearchapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
