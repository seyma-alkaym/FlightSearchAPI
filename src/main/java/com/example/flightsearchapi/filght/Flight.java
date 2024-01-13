package com.example.flightsearchapi.filght;

import com.example.flightsearchapi.airport.Airport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "departure_airport_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Airport departureAirport;

    @JoinColumn(name = "arrival_airport_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Airport arrivalAirport;

    @Column(name = "departure_date_time")
    private LocalDateTime departureDateTime;

    @Column(name = "return_date_time")
    private LocalDateTime returnDateTime;

    private BigDecimal price;
}
