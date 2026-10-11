package com.movieticket.service;

import com.movieticket.model.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTest {

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
    void calculatePriceForSelectedSeats() {

        BookingService service = new BookingService();

        Show show = createShow();

        List<Seat> seats = Arrays.asList(
                new Seat("A1"),
                new Seat("A2")
        );

        double price = service.calculatePrice(show, seats);

        assertEquals(500, price);
    }

    @Test
    void availableSeatsShouldReturnTrue() {

        BookingService service = new BookingService();

        List<Seat> seats = Arrays.asList(
                new Seat("A1"),
                new Seat("A2")
        );

        assertTrue(service.areSeatsAvailable(seats));
    }

    @Test
    void occupiedSeatShouldReturnFalse() {

        BookingService service = new BookingService();

        Seat seat = new Seat("A1");
        seat.setAvailable(false);

        List<Seat> seats = List.of(seat);

        assertFalse(service.areSeatsAvailable(seats));
    }

    @Test
    void createBookingSuccessfully() {

        BookingService service = new BookingService();

        User user = createUser();
        Show show = createShow();

        List<Seat> seats = Arrays.asList(
                new Seat("A1"),
                new Seat("A2")
        );

        Booking booking =
                service.createBooking(user, show, seats);

        assertNotNull(booking);
        assertEquals("CONFIRMED", booking.getStatus());
        assertEquals(500, booking.getTotalAmount());
    }

    @Test
    void bookedSeatsBecomeUnavailable() {

        BookingService service = new BookingService();

        User user = createUser();
        Show show = createShow();

        Seat seat = new Seat("A1");

        List<Seat> seats = List.of(seat);

        service.createBooking(user, show, seats);

        assertFalse(seat.isAvailable());
    }

    @Test
    void cannotBookOccupiedSeat() {

        BookingService service = new BookingService();

        User user = createUser();
        Show show = createShow();

        Seat seat = new Seat("A1");
        seat.setAvailable(false);

        List<Seat> seats = List.of(seat);

        Booking booking =
                service.createBooking(user, show, seats);

        assertNull(booking);
    }

    @Test
    void cancelBookingSuccessfully() {

        BookingService service = new BookingService();

        User user = createUser();
        Show show = createShow();

        Seat seat = new Seat("A1");

        Booking booking =
                service.createBooking(user, show, List.of(seat));

        boolean result =
                service.cancelBooking(booking.getBookingId());

        assertTrue(result);
        assertEquals("CANCELLED", booking.getStatus());
        assertTrue(seat.isAvailable());
    }

    @Test
    void cannotCancelBookingTwice() {

        BookingService service = new BookingService();

        User user = createUser();
        Show show = createShow();

        Seat seat = new Seat("A1");

        Booking booking =
                service.createBooking(user, show, List.of(seat));

        service.cancelBooking(booking.getBookingId());

        boolean secondCancellation =
                service.cancelBooking(booking.getBookingId());

        assertFalse(secondCancellation);
    }

    @Test
    void bookingHistoryReturnsUserBookings() {

        BookingService service = new BookingService();

        User user = createUser();
        Show show = createShow();

        Seat seat = new Seat("A1");

        service.createBooking(user, show, List.of(seat));

        List<Booking> history =
                service.getBookingHistory(user);

        assertEquals(1, history.size());
    }
}