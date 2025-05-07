package com.gridnine.testing;

import java.time.Duration;
import java.util.List;

public class GroundTimeExceedsTwoHoursFilter implements FlightFilter {
    @Override
    public boolean shouldFilter(Flight flight) {
        List<Segment> segments = flight.getSegments();
        if (segments.size() < 2) return false;

        long totalGroundMinutes = 0;
        for (int i = 0; i < segments.size() - 1; i++) {
            Segment current = segments.get(i);
            Segment next = segments.get(i + 1);
            Duration groundTime = Duration.between(current.getArrivalDate(), next.getDepartureDate());
            totalGroundMinutes += groundTime.toMinutes();
        }
        return totalGroundMinutes > 120;
    }
}
