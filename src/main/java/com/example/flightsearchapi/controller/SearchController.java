package com.example.flightsearchapi.controller;

import com.example.flightsearchapi.entity.Flight;
import com.example.flightsearchapi.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;
    @GetMapping("")
    public ResponseEntity<Object> searchFlights(
            @RequestParam String arrivalCity,
            @RequestParam String departureCity,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime departureDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime returnDate) {

        if (returnDate == null) {
            List<Flight> oneWayFlights = searchService.searchOneWayFlights(arrivalCity, departureCity, departureDate);
            if (oneWayFlights.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No one-way flights found.");
            } else {
                return ResponseEntity.ok(oneWayFlights);
            }
        } else {
            List<Flight> roundTripFlights = searchService
                    .searchRoundTripFlights(arrivalCity, departureCity, departureDate, returnDate);
            if (roundTripFlights.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No round-trip flights found.");
            } else {
                return ResponseEntity.ok(roundTripFlights);
            }
        }
    }
}
