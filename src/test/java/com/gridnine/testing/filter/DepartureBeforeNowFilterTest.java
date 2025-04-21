package com.gridnine.testing.filter;

import com.gridnine.testing.test.TestUtils;
import com.gridnine.testing.model.Flight;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DepartureBeforeNowFilterTest {
    private final DepartureBeforeNowFilter filter = new DepartureBeforeNowFilter();

    @Test
    void testFlightWithPastDepartureShouldBeFilteredOut() {
        LocalDateTime now = LocalDateTime.now();
        Flight flight = TestUtils.createFlight(now.minusDays(1), now.plusHours(1));
        assertFalse(filter.matches(flight));
    }

    @Test
    void testFlightWithFutureDepartureShouldBeValid() {
        LocalDateTime now = LocalDateTime.now();
        Flight flight = TestUtils.createFlight(now.plusHours(1), now.plusHours(2));
        assertTrue(filter.matches(flight));
    }
}

