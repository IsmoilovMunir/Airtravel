package com.gridnine.testing;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.filter.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.filter.DepartureBeforeNowFilter;
import com.gridnine.testing.filter.ExcessiveGroundTimeFilter;
import com.gridnine.testing.model.Flight;
import com.gridnine.testing.service.FlightFilterService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlightTestDataFactory();
        FlightFilterService filterService = new FlightFilterService();

        System.out.println("Все полёты:");
        flights.forEach(System.out::println);

        System.out.println("\n1. Исключить вылеты до текущего времени:");
        filterService.filter(flights, new DepartureBeforeNowFilter())
                .forEach(System.out::println);

        System.out.println("\n2. Исключить полёты с сегментами, где прилёт раньше вылета:");
        filterService.filter(flights, new ArrivalBeforeDepartureFilter())
                .forEach(System.out::println);

        System.out.println("\n3. Исключить полёты, где общее время на земле больше 2 часов:");
        filterService.filter(flights, new ExcessiveGroundTimeFilter())
                .forEach(System.out::println);
    }

}