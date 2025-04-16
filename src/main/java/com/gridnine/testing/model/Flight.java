package com.gridnine.testing.model;

import java.util.List;


public class Flight {
    private final List<Segment> segments;

    public Flight(List<Segment> segments) {
        this.segments = segments;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    @Override
    public String toString() {
        return String.join(" ", segments.stream().map(Segment::toString).toList());
    }
}
