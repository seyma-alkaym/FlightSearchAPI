package com.example.flightsearchapi.repository;

import com.example.flightsearchapi.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    boolean existsByCity(String city);
    @Query("SELECT a FROM Airport a WHERE a.city = :city")
    Optional<Airport> findByCity(@Param("city") String city);
}
