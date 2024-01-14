package com.example.flightsearchapi.service;

import com.example.flightsearchapi.dto.FlightDto;
import com.example.flightsearchapi.entity.Airport;
import com.example.flightsearchapi.entity.Flight;
import com.example.flightsearchapi.repository.AirportRepository;
import com.example.flightsearchapi.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public List<Flight> getAllFlights() {

        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id){
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Id Was Provided"));
    }

    private Airport createOrUpdateAirport(String airportCity) {
        return airportRepository.findByCity(airportCity)
                .map(existingAirport -> {
                    // If the airport exists, return it
                    existingAirport.setIsDeleted(false);
                    return existingAirport;
                })
                .orElseGet(() -> {
                    // If the airport does not exist, create a new one
                    Airport airport = Airport.builder()
                            .city(airportCity)
                            .isDeleted(false)
                            .build();

                    airportRepository.save(airport);

                    return airport;
                });
    }


    public Flight create(FlightDto flightDto) {
        Airport arrivalAirport;
        Airport departureAirport;

        arrivalAirport = createOrUpdateAirport(flightDto.getArrivalAirport());
        departureAirport = createOrUpdateAirport(flightDto.getDepartureAirport());

        Flight flight = Flight.builder()
                .arrivalAirport(arrivalAirport)
                .departureAirport(departureAirport)
                .departureDateTime(flightDto.getDepartureDateTime())
                .returnDateTime(flightDto.getReturnDateTime())
                .price(flightDto.getPrice())
                .isDeleted(false)
                .build();

        flightRepository.save(flight);

        return flight;
    }

    public Flight update(FlightDto flightDto, Long id) {
        Flight flight = flightRepository
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("Invalid Id Was Provided"));

        Airport arrivalAirport = createOrUpdateAirport(flightDto.getArrivalAirport());
        Airport departureAirport = createOrUpdateAirport(flightDto.getDepartureAirport());

        flight.setDepartureDateTime(flightDto.getDepartureDateTime());
        flight.setReturnDateTime(flightDto.getReturnDateTime());
        flight.setPrice(flightDto.getPrice());
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureAirport(departureAirport);

        flightRepository.save(flight);

        return flight;
    }

    public ResponseEntity<?> delete(Long id){
       Flight flight = flightRepository
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("Invalid Id was provided"));

       flight.setIsDeleted(true);

       flightRepository.save(flight);

       //Or
       /*
       flightRepository.deleteById(id); //I wouldn't use it because it usually doesn't delete directly from the database
        */

       MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
       headers.add("Delete-Massage", "The flight has been deleted successfully");

       return new ResponseEntity<>("",
               new HttpHeaders(headers),
               HttpStatus.NO_CONTENT);
    }
}
