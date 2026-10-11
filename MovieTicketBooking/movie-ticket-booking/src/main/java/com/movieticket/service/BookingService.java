package com.movieticket.service;

import com.movieticket.model.Booking;
import com.movieticket.model.Seat;
import com.movieticket.model.Show;
import com.movieticket.model.User;

import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private List<Booking> bookings = new ArrayList<>();
    private int nextBookingId = 1;

    public double calculatePrice(Show show, List<Seat> seats) {

        if (show == null || seats == null || seats.isEmpty()) {
            return 0;
        }

        return show.getTicketPrice() * seats.size();
    }

    public boolean areSeatsAvailable(List<Seat> seats) {

        if (seats == null || seats.isEmpty()) {
            return false;
        }

        for (Seat seat : seats) {

            if (seat == null || !seat.isAvailable()) {
                return false;
            }
        }

        return true;
    }

    public boolean isSeatAlreadyBooked(Show show, String seatNumber) {

        if (show == null || seatNumber == null) {
            return false;
        }

        for (Booking booking : bookings) {

            if (booking.getShow().getShowId() == show.getShowId()
                    && !booking.getStatus().equals("CANCELLED")) {

                for (Seat seat : booking.getSeats()) {

                    if (seat.getSeatNumber()
                            .equalsIgnoreCase(seatNumber)) {

                        return true;
                    }
                }
            }
        }

        return false;
    }

    public Booking createBooking(User user, Show show, List<Seat> seats) {

        if (user == null || show == null || !areSeatsAvailable(seats)) {
            return null;
        }

        for (Seat seat : seats) {

            if (isSeatAlreadyBooked(show, seat.getSeatNumber())) {
                return null;
            }
        }

        double totalAmount = calculatePrice(show, seats);

        Booking booking = new Booking(
                nextBookingId++,
                user,
                show,
                new ArrayList<>(seats),
                totalAmount,
                "CONFIRMED"
        );

        for (Seat seat : seats) {
            seat.setAvailable(false);
        }

        bookings.add(booking);

        return booking;
    }

    public Booking createBookingAfterPayment(
            User user,
            Show show,
            List<Seat> seats,
            PaymentService paymentService) {

        if (user == null || show == null || !areSeatsAvailable(seats)) {
            return null;
        }

        for (Seat seat : seats) {

            if (isSeatAlreadyBooked(show, seat.getSeatNumber())) {
                return null;
            }
        }

        double totalAmount = calculatePrice(show, seats);

        boolean paymentSuccessful =
                paymentService.processPayment(totalAmount);

        if (!paymentSuccessful) {
            return null;
        }

        Booking booking = new Booking(
                nextBookingId++,
                user,
                show,
                new ArrayList<>(seats),
                totalAmount,
                "CONFIRMED"
        );

        for (Seat seat : seats) {
            seat.setAvailable(false);
        }

        bookings.add(booking);

        return booking;
    }

    public boolean cancelBooking(int bookingId) {

        for (Booking booking : bookings) {

            if (booking.getBookingId() == bookingId) {

                if (booking.getStatus().equals("CANCELLED")) {
                    return false;
                }

                booking.setStatus("CANCELLED");

                for (Seat seat : booking.getSeats()) {
                    seat.setAvailable(true);
                }

                return true;
            }
        }

        return false;
    }

    public List<Booking> getBookingHistory(User user) {

        List<Booking> history = new ArrayList<>();

        if (user == null) {
            return history;
        }

        for (Booking booking : bookings) {

            if (booking.getUser().getUserId() == user.getUserId()) {
                history.add(booking);
            }
        }

        return history;
    }
}