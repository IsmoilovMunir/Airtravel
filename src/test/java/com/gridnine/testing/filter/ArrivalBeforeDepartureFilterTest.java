package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArrivalBeforeDepartureFilterTest {
    @Test
    void testFlightWithArrivalBeforeDepartureShouldBeFilteredOut() {
        LocalDateTime departure = LocalDateTime.now().plusHours(2);
        LocalDateTime arrival = LocalDateTime.now();
        Segment invalidSegment = new Segment(departure, arrival);
        Flight flight = new Flight(List.of(invalidSegment));

        ArrivalBeforeDepartureFilter filter = new ArrivalBeforeDepartureFilter();
        assertFalse(filter.matches(flight), "Рейс с прилётом раньше вылета должен быть отфильтрован (false)");
    }



    @Test
    void testFlightWithValidSegmentsShouldPass() {
        LocalDateTime departure = LocalDateTime.of(2025, 4, 16, 12, 0);
        LocalDateTime arrival = LocalDateTime.of(2025, 4, 16, 14, 0);
        Segment validSegment = new Segment(departure, arrival);
        Flight flight = new Flight(List.of(validSegment));

        ArrivalBeforeDepartureFilter filter = new ArrivalBeforeDepartureFilter();
        assertTrue(filter.matches(flight), "Рейс с корректными сегментами должен быть пропущен (true)");
    }
    @Test
    void testFlightWithOneInvalidSegmentAmongManyShouldBeFilteredOut() {
        LocalDateTime base = LocalDateTime.of(2025, 4, 16, 10, 0);
        Segment valid = new Segment(base, base.plusHours(2));
        Segment invalid = new Segment(base.plusHours(3), base.plusHours(2)); // прибытие раньше вылета

        Flight flight = new Flight(List.of(valid, invalid));
        ArrivalBeforeDepartureFilter filter = new ArrivalBeforeDepartureFilter();

        assertFalse(filter.matches(flight), "Рейс с хотя бы одним некорректным сегментом должен быть отфильтрован");
    }
}
