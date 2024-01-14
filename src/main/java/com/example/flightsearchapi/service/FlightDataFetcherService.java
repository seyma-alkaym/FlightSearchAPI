package com.example.flightsearchapi.service;

import com.example.flightsearchapi.entity.Airport;
import com.example.flightsearchapi.entity.Flight;
import com.example.flightsearchapi.repository.AirportRepository;
import com.example.flightsearchapi.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class FlightDataFetcherService {
    private final RestTemplate restTemplate;
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    @Scheduled(cron = "0 0 0 * * ?") //Daily at midnight
    public void fetchDataAndSaveToDatabase() {
        String mockApiUrl = "https://run.mocky.io/v3/5692fa4d-b1af-4531-8448-c4f9ff5af7ef";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(headers);

        Flight[] flightsResponse = restTemplate.exchange(
                mockApiUrl, HttpMethod.GET, entity, Flight[].class).getBody();

        if (flightsResponse == null) throw new AssertionError();
        for (Flight flight : flightsResponse) {
            Airport arrivalAirport = flight.getArrivalAirport();
            Airport departureAirport = flight.getDepartureAirport();

            // Check if the arrival airport exists, otherwise save or update it
            if (!airportRepository.existsByCity(arrivalAirport.getCity())) {
                arrivalAirport = airportRepository.save(arrivalAirport);
            } else {
                arrivalAirport = airportRepository.findByCity(arrivalAirport.getCity())
                        .orElseThrow(() -> new RuntimeException("Arrival Airport does not exist!"));
            }

            // Check if the departure airport exists, otherwise save or update it
            if (!airportRepository.existsByCity(departureAirport.getCity())) {
                departureAirport = airportRepository.save(departureAirport);
            } else {
                departureAirport = airportRepository.findByCity(departureAirport.getCity())
                        .orElseThrow(() -> new RuntimeException("Departure Airport does not exist!"));
            }


            // Create a new flight with the updated airports and save it
            Flight newFlight = Flight.builder()
                    .arrivalAirport(arrivalAirport)
                    .departureAirport(departureAirport)
                    .departureDateTime(flight.getDepartureDateTime())
                    .returnDateTime(flight.getReturnDateTime())
                    .price(flight.getPrice())
                    .isDeleted(false)
                    .build();

            flightRepository.save(newFlight);
        }
    }
}
