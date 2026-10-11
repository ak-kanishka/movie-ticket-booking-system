package com.movieticket.service;

import com.movieticket.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DuplicateSeatBookingTest {

    @Test
    void sameSeatCannotBeBookedTwiceForSameShow() {

        BookingService bookingService = new BookingService();
        PaymentService paymentService = new PaymentService();

        User user = new User(
                1,
                "Kanishka",
                "kanishka@gmail.com",
                "password123"
        );

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

        Show show = new Show(
                1,
                movie,
                theatre,
                "10-10-2026",
                "6:30 PM",
                250
        );

        Seat firstSeat = new Seat("A1");

        Booking firstBooking =
                bookingService.createBookingAfterPayment(
                        user,
                        show,
                        List.of(firstSeat),
                        paymentService
                );

        assertNotNull(firstBooking);

        Seat secondSeat = new Seat("A1");

        Booking secondBooking =
                bookingService.createBookingAfterPayment(
                        user,
                        show,
                        List.of(secondSeat),
                        paymentService
                );

        assertNull(secondBooking);
    }
}