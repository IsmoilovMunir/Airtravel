package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

public class ArrivalBeforeDepartureFilter implements FlightFilter {
    @Override
    public boolean isValid(Flight flight) {
        return flight.getSegments().stream()
                .allMatch(segment ->
                        !segment.getArrivalDate().isBefore(segment.getDepartureDate()));
    }
}
