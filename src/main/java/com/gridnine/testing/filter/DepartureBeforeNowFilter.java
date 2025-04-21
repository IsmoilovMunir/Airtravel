package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.time.LocalDateTime;

public class DepartureBeforeNowFilter implements FlightFilter {
    @Override
    public boolean matches(Flight flight) {
        return flight.getSegments().stream()
                .noneMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now()));
    }
}
