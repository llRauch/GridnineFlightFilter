package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterProcessor {
    public List<Flight> filter(List<Flight> flights, FlightFilter filter) {
        return flights.stream()
                .filter(flight -> !filter.shouldFilter(flight))
                .collect(Collectors.toList());
    }
}
