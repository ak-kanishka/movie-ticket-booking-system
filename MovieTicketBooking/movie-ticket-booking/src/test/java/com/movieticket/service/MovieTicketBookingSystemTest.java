package com.movieticket.service;

import com.movieticket.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTicketBookingSystemTest {

    @Test
    void completeMovieTicketBookingFlow() {

        // 1. Create services
        UserService userService = new UserService();
        MovieService movieService = new MovieService();
        BookingService bookingService = new BookingService();
        PaymentService paymentService = new PaymentService();

        // 2. Register user
        User user = new User(
                1,
                "Kanishka",
                "kanishka@gmail.com",
                "password123"
        );

        assertTrue(userService.register(user));

        // 3. Login
        assertTrue(
                userService.login(
                        "kanishka@gmail.com",
                        "password123"
                )
        );

        // 4. Add and search movie
        Movie movie = new Movie(
                1,
                "Interstellar",
                "Sci-Fi",
                "English",
                169
        );

        movieService.addMovie(movie);

        List<Movie> movies =
                movieService.searchMovies("Inter");

        assertEquals(1, movies.size());

        // 5. Create theatre and show
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

        // 6. Select seats
        Seat seat1 = new Seat("A1");
        Seat seat2 = new Seat("A2");

        List<Seat> selectedSeats =
                List.of(seat1, seat2);

        // 7. Calculate price
        double amount =
                bookingService.calculatePrice(
                        show,
                        selectedSeats
                );

        assertEquals(500, amount);

        // 8. Make payment
        boolean paymentSuccessful =
                paymentService.processPayment(amount);

        assertTrue(paymentSuccessful);

        // 9. Create booking
        Booking booking =
                bookingService.createBooking(
                        user,
                        show,
                        selectedSeats
                );

        assertNotNull(booking);
        assertEquals("CONFIRMED", booking.getStatus());

        // 10. Verify seats are occupied
        assertFalse(seat1.isAvailable());
        assertFalse(seat2.isAvailable());

        // 11. Verify booking history
        List<Booking> history =
                bookingService.getBookingHistory(user);

        assertEquals(1, history.size());

        // 12. Cancel booking
        assertTrue(
                bookingService.cancelBooking(
                        booking.getBookingId()
                )
        );

        // 13. Verify cancellation
        assertEquals("CANCELLED", booking.getStatus());

        // 14. Verify seats become available again
        assertTrue(seat1.isAvailable());
        assertTrue(seat2.isAvailable());
    }
}