package com.gridnine.testing.test;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;

public class TestUtils {
    public static Flight createFlight(LocalDateTime... dates) {
        if (dates.length % 2 != 0) {
            throw new IllegalArgumentException("Must provide even number of dates");
        }

        var segments = new java.util.ArrayList<Segment>();
        for (int i = 0; i < dates.length; i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }

        return new Flight(segments);
    }
}
