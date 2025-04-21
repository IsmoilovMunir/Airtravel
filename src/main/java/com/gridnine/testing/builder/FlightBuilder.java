package com.gridnine.testing.builder;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlightBuilder {
    public static List<Flight> createFlightTestDataFactory() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);

        return Arrays.asList(
                // 1. Обычный перелёт на 2 часа
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),

                // 2. Мультисегментный перелёт
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),

                // 3. Перелёт с вылетом в прошлом
                createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),

                // 4. Перелёт, где прилёт раньше вылета
                createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),

                // 5. Перелёт с более чем 2 часами на земле
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),

                // 6. Ещё один перелёт с длительным ожиданием между сегментами
                createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                        threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                        threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7))
        );
    }

    /**
     * Создаёт перелёт с заданными временными отрезками (каждая пара — сегмент).
     */
    private static Flight createFlight(LocalDateTime... dates) {
        if (dates.length % 2 != 0) {
            throw new IllegalArgumentException("Необходимо передать чётное количество дат (вылет-прилёт)");
        }

        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < dates.length - 1; i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }

        return new Flight(segments);
    }
}
