package com.movieticket.service;

import com.movieticket.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingDecisionTableTest {

    private Show createShow() {

        Movie movie = new Movie(
                1,
                "Interstellar",
                "Sci-Fi",
                "English",
                169
        );

        Theatre theatre = new Theatre(
                1,
                "PVR Cinemas",
                "Chennai"
        );

        return new Show(
                1,
                movie,
                theatre,
                "10-10-2026",
                "6:30 PM",
                250
        );
    }

    @Test
    void validUserValidShowAvailableSeat() {

        BookingService service = new BookingService();

        User user = new User(
                1,
                "Kanishka",
                "kanishka@gmail.com",
                "password123"
        );

        Seat seat = new Seat("A1");

        Booking booking =
                service.createBooking(
                        user,
                        createShow(),
                        List.of(seat)
                );

        assertNotNull(booking);
    }

    @Test
    void nullUserAvailableSeat() {

        BookingService service = new BookingService();

        Seat seat = new Seat("A1");

        Booking booking =
                service.createBooking(
                        null,
                        createShow(),
                        List.of(seat)
                );

        assertNull(booking);
    }

    @Test
    void validUserOccupiedSeat() {

        BookingService service = new BookingService();

        User user = new User(
                1,
                "Kanishka",
                "kanishka@gmail.com",
                "password123"
        );

        Seat seat = new Seat("A1");
        seat.setAvailable(false);

        Booking booking =
                service.createBooking(
                        user,
                        createShow(),
                        List.of(seat)
                );

        assertNull(booking);
    }

    @Test
    void nullShowAvailableSeat() {

        BookingService service = new BookingService();

        User user = new User(
                1,
                "Kanishka",
                "kanishka@gmail.com",
                "password123"
        );

        Seat seat = new Seat("A1");

        Booking booking =
                service.createBooking(
                        user,
                        null,
                        List.of(seat)
                );

        assertNull(booking);
    }
}
