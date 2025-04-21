package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

public class ArrivalBeforeDepartureFilter implements FlightFilter {
    @Override
    public boolean matches(Flight flight) {
        return flight.getSegments().stream()
                .allMatch(segment ->
                        !segment.getArrivalDate().isBefore(segment.getDepartureDate()));
    }
}
