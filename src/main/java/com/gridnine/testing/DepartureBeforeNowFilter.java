package com.gridnine.testing;

import java.time.LocalDateTime;

public class DepartureBeforeNowFilter implements FlightFilter{
    @Override
    public boolean shouldFilter(Flight flight) {
        LocalDateTime now = LocalDateTime.now();
        return flight.getSegments().stream()
                .anyMatch(segment -> segment.getDepartureDate().isBefore(now));
    }
}
