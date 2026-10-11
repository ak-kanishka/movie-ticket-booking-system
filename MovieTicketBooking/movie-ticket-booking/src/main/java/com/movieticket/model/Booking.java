package com.movieticket.model;

import java.util.List;

public class Booking {

    private int bookingId;
    private User user;
    private Show show;
    private List<Seat> seats;
    private double totalAmount;
    private String status;

    public Booking(int bookingId, User user, Show show,
                   List<Seat> seats, double totalAmount, String status) {

        this.bookingId = bookingId;
        this.user = user;
        this.show = show;
        this.seats = seats;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}