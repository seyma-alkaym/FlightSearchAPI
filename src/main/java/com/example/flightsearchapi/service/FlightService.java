package com.example.flightsearchapi.service;

import com.example.flightsearchapi.entity.Flight;
import com.example.flightsearchapi.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id){
        return flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Id Was Provided"));
    }
}
