package com.gridnine.testing;

public class ArrivalBeforeDepartureFilter implements FlightFilter{
    @Override
    public boolean shouldFilter(Flight flight) {
        return flight.getSegments().stream()
                .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));
    }
}
