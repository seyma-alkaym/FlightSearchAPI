package com.example.flightsearchapi.controller;

import com.example.flightsearchapi.dto.FlightDto;
import com.example.flightsearchapi.entity.Flight;
import com.example.flightsearchapi.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/get-flight/{id}")
    public ResponseEntity<?> getFlightById(@PathVariable Long id){
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @PostMapping("/create-flight")
    public ResponseEntity<?> create(@RequestBody FlightDto flightDto) {
        Flight flight = flightService.create(flightDto);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Add-Massage", "The flight has been added successfully.");


        return new ResponseEntity<>(flight, new HttpHeaders(headers), HttpStatus.CREATED);
    }

    @PutMapping("/update-flight/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody FlightDto flightDto){
        Flight flight = flightService.update(flightDto, id);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Update-Massage", "The flight has been updated successfully.");


        return new ResponseEntity<>(flight, new HttpHeaders(headers), HttpStatus.OK);
    }

    @DeleteMapping("/delete-flight/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return flightService.delete(id);
    }
}
