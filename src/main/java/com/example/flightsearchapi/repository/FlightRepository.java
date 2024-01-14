package com.example.flightsearchapi.repository;


import com.example.flightsearchapi.entity.Airport;
import com.example.flightsearchapi.entity.Flight;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findAllByArrivalAirportAndDepartureAirportAndDepartureDateTime(
            Airport arrivalAirport,
            Airport departureAirport,
            LocalDateTime departureDate
    );

    List<Flight> findAllByArrivalAirportAndDepartureAirportAndDepartureDateTimeAndReturnDateTime(
            Airport arrivalAirport,
            Airport departureAirport,
            LocalDateTime departureDate,
            LocalDateTime returnDate);

}
