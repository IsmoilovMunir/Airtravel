package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.util.List;

public class ExcessiveGroundTimeFilter implements FlightFilter{
    @Override
    public boolean isValid(Flight flight) {
        List<Segment> segments = flight.getSegments();
        long totalGroundTimeMinutes = 0;
        for (int i = 0; i < segments.size() - 1; i++) {
            var arrival = segments.get(i).getArrivalDate();
            var nextDeparture = segments.get(i + 1).getDepartureDate();
            totalGroundTimeMinutes += Duration.between(arrival, nextDeparture).toMinutes();
        }
        return totalGroundTimeMinutes <= 120;
    }
}
