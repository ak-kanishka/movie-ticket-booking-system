package com.movieticket.service;

import com.movieticket.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingStateTransitionTest {

    private User createUser() {
        return new User(
                1,
                "Kanishka",
                "kanishka@gmail.com",
                "password123"
        );
    }

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
    void seatAvailableToBooked() {

        BookingService service = new BookingService();

        Seat seat = new Seat("A1");

        assertTrue(seat.isAvailable());

        service.createBooking(
                createUser(),
                createShow(),
                List.of(seat)
        );

        assertFalse(seat.isAvailable());
    }

    @Test
    void bookedToCancelled() {

        BookingService service = new BookingService();

        Seat seat = new Seat("A1");

        Booking booking =
                service.createBooking(
                        createUser(),
                        createShow(),
                        List.of(seat)
                );

        assertFalse(seat.isAvailable());

        service.cancelBooking(booking.getBookingId());

        assertEquals("CANCELLED", booking.getStatus());
    }

    @Test
    void cancelledSeatBecomesAvailable() {

        BookingService service = new BookingService();

        Seat seat = new Seat("A1");

        Booking booking =
                service.createBooking(
                        createUser(),
                        createShow(),
                        List.of(seat)
                );

        service.cancelBooking(booking.getBookingId());

        assertTrue(seat.isAvailable());
    }

    @Test
    void cannotCancelAlreadyCancelledBooking() {

        BookingService service = new BookingService();

        Seat seat = new Seat("A1");

        Booking booking =
                service.createBooking(
                        createUser(),
                        createShow(),
                        List.of(seat)
                );

        service.cancelBooking(booking.getBookingId());

        boolean secondCancellation =
                service.cancelBooking(booking.getBookingId());

        assertFalse(secondCancellation);
    }
}