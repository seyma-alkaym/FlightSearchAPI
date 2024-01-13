package com.example.flightsearchapi.filght;


import org.springframework.data.jpa.repository.JpaRepository;

public interface IFlightRepository extends JpaRepository<Flight, Long> {
}
