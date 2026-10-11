package com.movieticket.service;

import com.movieticket.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingPaymentIntegrationTest {

    @Test
    void bookingShouldBeConfirmedAfterSuccessfulPayment() {

        UserService userService = new UserService();
        BookingService bookingService = new BookingService();
        PaymentService paymentService = new PaymentService();

        User user = new User(
                1,
                "Kanishka",
                "kanishka@gmail.com",
                "password123"
        );

        userService.register(user);

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

        Seat seat = new Seat("A1");

        Booking booking =
        bookingService.createBookingAfterPayment(
                user,
                show,
                List.of(seat),
                paymentService
        );

        

        assertNotNull(booking);
        assertEquals("CONFIRMED", booking.getStatus());
    }
}