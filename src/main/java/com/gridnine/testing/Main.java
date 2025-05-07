package com.gridnine.testing;

import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightFilterProcessor processor = new FlightFilterProcessor();

        System.out.println("--- Исходный набор перелетов (" + flights.size() + " рейсов) ---");
        printFlights(flights);

        System.out.println("\n--- Фильтр 1: Вылет до текущего момента (оставшиеся рейсы) ---");
        List<Flight> filtered1 = processor.filter(flights, new DepartureBeforeNowFilter());
        printFilterSummary(flights.size(), filtered1.size());
        printFlights(filtered1);

        System.out.println("\n--- Фильтр 2: Сегменты с датой прилёта раньше даты вылета (оставшиеся рейсы) ---");
        List<Flight> filtered2 = processor.filter(flights, new ArrivalBeforeDepartureFilter());
        printFilterSummary(flights.size(), filtered2.size());
        printFlights(filtered2);

        System.out.println("\n--- Фильтр 3: Общее время на земле не более 2 часов (оставшиеся рейсы) ---");
        List<Flight> filtered3 = processor.filter(flights, new GroundTimeExceedsTwoHoursFilter());
        printFilterSummary(flights.size(), filtered3.size());
        printFlights(filtered3);

    }

    private static void printFlights(List<Flight> flights) {
        if (flights.isEmpty()) {
            System.out.println("  Нет перелетов, удовлетворяющих условиям.");
        } else {
            IntStream.range(0, flights.size())
                    .forEach(i -> System.out.println("  " + (i + 1) + ". " + flights.get(i)));
        }
    }

    private static void printFilterSummary(int originalSize, int filteredSize) {
        System.out.println("  (Найдено " + filteredSize + " из " + originalSize + " рейсов)");
    }
}
