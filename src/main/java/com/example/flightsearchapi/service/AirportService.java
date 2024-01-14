package com.example.flightsearchapi.service;

import com.example.flightsearchapi.dto.AirportDto;
import com.example.flightsearchapi.entity.Airport;
import com.example.flightsearchapi.repository.AirportRepository;
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
public class AirportService {
    private final AirportRepository airportRepository;

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Airport getAirportById(Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Id Was Provided"));
    }

    public Airport create(AirportDto airportDto) {
        Airport airport = Airport.builder()
                .city(airportDto.getCity())
                .isDeleted(false)
                .build();
        airportRepository.save(airport);
        return airport;
    }

    public Airport update(AirportDto airportDto, Long id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Id Was Provided"));

        airport.setCity(airportDto.getCity());

        airportRepository.save(airport);

        return airport;
    }

    public ResponseEntity<?> delete(Long id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Id Was Provided"));
        airport.setIsDeleted(true);

        airportRepository.save(airport);

        MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
        headers.add("Delete-Massage", "The airport has been deleted successfully");

        return new ResponseEntity<>("",
                new HttpHeaders(headers),
                HttpStatus.NO_CONTENT);
    }
}
