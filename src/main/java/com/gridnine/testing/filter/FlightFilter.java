package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

public interface FlightFilter {
    boolean isValid(Flight flight);
}
