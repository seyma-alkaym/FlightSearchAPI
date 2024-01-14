package com.example.flightsearchapi.controller;

import com.example.flightsearchapi.dto.AirportDto;
import com.example.flightsearchapi.entity.Airport;
import com.example.flightsearchapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/airports")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping("/get-airport/{id}")
    public ResponseEntity<?> getAirportById(@PathVariable Long id){
        return ResponseEntity.ok(airportService.getAirportById(id));
    }

    @PostMapping("/create-airport")
    public ResponseEntity<?> create(@RequestBody AirportDto airportDto) {
        Airport airport = airportService.create(airportDto);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Add-Massage", "The airport has been added successfully.");

        return new ResponseEntity<>(airport, new HttpHeaders(headers), HttpStatus.CREATED);
    }

    @PutMapping("/update-airport/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody AirportDto airportDto){
        Airport airport = airportService.update(airportDto, id);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Update-Massage", "The airport has been updated successfully.");

        return new ResponseEntity<>(airport, new HttpHeaders(headers), HttpStatus.OK);
    }

    @DeleteMapping("/delete-airport/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return airportService.delete(id);
    }
}
