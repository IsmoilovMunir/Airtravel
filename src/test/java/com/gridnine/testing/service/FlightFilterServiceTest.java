package com.gridnine.testing.service;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.filter.DepartureBeforeNowFilter;
import com.gridnine.testing.filter.ExcessiveGroundTimeFilter;
import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightFilterServiceTest {
    private List<Flight> testFlights;
    private FlightFilterService filterService;

    @BeforeEach
    void setup() {
        testFlights = FlightBuilder.createFlights();
        filterService = new FlightFilterService();
    }

    @Test
    void testDepartureBeforeNowFilter() {
        var filtered = filterService.filter(testFlights, new DepartureBeforeNowFilter());
        assertTrue(filtered.stream().noneMatch(
                f -> f.getSegments().get(0).getDepartureDate().isBefore(java.time.LocalDateTime.now()))
        );
    }

    @Test
    void testArrivalBeforeDepartureFilter() {
        var filtered = filterService.filter(testFlights, new ArrivalBeforeDepartureFilter());
        assertTrue(filtered.stream().noneMatch(
                f -> f.getSegments().stream().anyMatch(
                        s -> s.getArrivalDate().isBefore(s.getDepartureDate()))
        ));
    }


}
