package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.test.TestUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExcessiveGroundTimeFilterTest {
    private final ExcessiveGroundTimeFilter filter = new ExcessiveGroundTimeFilter();

    @Test
    void testFlightWithShortGroundTimeShouldPass() {
        LocalDateTime now = LocalDateTime.now();
        Flight flight = TestUtils.createFlight(
                now, now.plusHours(2),
                now.plusHours(3), now.plusHours(5)
        );
        assertTrue(filter.matches(flight));
    }

    @Test
    void testFlightWithExcessiveGroundTimeShouldBeFilteredOut() {
        LocalDateTime now = LocalDateTime.now();
        Flight flight = TestUtils.createFlight(
                now, now.plusHours(2),
                now.plusHours(5), now.plusHours(6)
        );
        assertFalse(filter.matches(flight));
    }
}
