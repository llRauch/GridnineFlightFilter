package com.gridnine.testing;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FlightFilterTests {

    @Test
    void departureBeforeNowFilter_shouldFilterFlightsWithPastDepartures() {
        Flight flightWithPastDeparture = new Flight(List.of(
                new Segment(LocalDateTime.now().minusHours(2), LocalDateTime.now().plusHours(2))
        ));

        Flight flightInFuture = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3))
        ));

        DepartureBeforeNowFilter filter = new DepartureBeforeNowFilter();

        assertTrue(filter.shouldFilter(flightWithPastDeparture));
        assertFalse(filter.shouldFilter(flightInFuture));
    }

    @Test
    void arrivalBeforeDepartureFilter_shouldFilterInvalidSegments() {
        Flight invalidFlight = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now())
        ));

        Flight validFlight = new Flight(List.of(
                new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2))
        ));

        ArrivalBeforeDepartureFilter filter = new ArrivalBeforeDepartureFilter();

        assertTrue(filter.shouldFilter(invalidFlight));
        assertFalse(filter.shouldFilter(validFlight));
    }

    @Test
    void groundTimeExceedsTwoHoursFilter_shouldFilterFlightsWithExcessiveLayovers() {
        LocalDateTime now = LocalDateTime.now();

        Flight withLongGroundTime = new Flight(List.of(
                new Segment(now, now.plusHours(1)),
                new Segment(now.plusHours(4), now.plusHours(5)) // 3 часа на земле
        ));

        Flight withShortGroundTime = new Flight(List.of(
                new Segment(now, now.plusHours(1)),
                new Segment(now.plusHours(2), now.plusHours(3)) // 1 час на земле
        ));

        GroundTimeExceedsTwoHoursFilter filter = new GroundTimeExceedsTwoHoursFilter();

        assertTrue(filter.shouldFilter(withLongGroundTime));
        assertFalse(filter.shouldFilter(withShortGroundTime));
    }
}

