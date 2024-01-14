package com.example.flightsearchapi.service;

import com.example.flightsearchapi.entity.Airport;
import com.example.flightsearchapi.entity.Flight;
import com.example.flightsearchapi.repository.AirportRepository;
import com.example.flightsearchapi.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public List<Flight> searchOneWayFlights(String arrivalCity, String departureCity, LocalDateTime departureDate) {
        Airport departureAirport = airportRepository
                .findByCity(departureCity)
                .orElseThrow(() -> new RuntimeException("Airport does not exist!"));
        Airport arrivalAirport = airportRepository
                .findByCity(arrivalCity)
                .orElseThrow(() -> new RuntimeException("Airport does not exist!"));

        return flightRepository
                .findAllByArrivalAirportAndDepartureAirportAndDepartureDateTime(
                        arrivalAirport,
                        departureAirport,
                        departureDate);
    }

    public List<Flight> searchRoundTripFlights(String arrivalCity, String departureCity, LocalDateTime departureDate, LocalDateTime returnDate) {
        Airport departureAirport = airportRepository
                .findByCity(departureCity)
                .orElseThrow(() -> new RuntimeException("Airport does not exist!"));
        Airport arrivalAirport = airportRepository
                .findByCity(arrivalCity)
                .orElseThrow(() -> new RuntimeException("Airport does not exist!"));

        return flightRepository.findAllByArrivalAirportAndDepartureAirportAndDepartureDateTimeAndReturnDateTime(
                arrivalAirport,
                departureAirport,
                departureDate,
                returnDate);
    }
}
