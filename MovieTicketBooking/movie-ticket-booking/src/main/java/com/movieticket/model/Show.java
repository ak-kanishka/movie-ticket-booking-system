package com.movieticket.model;

public class Show {

    private int showId;
    private Movie movie;
    private Theatre theatre;
    private String date;
    private String time;
    private double ticketPrice;

    public Show(int showId, Movie movie, Theatre theatre,
                String date, String time, double ticketPrice) {

        this.showId = showId;
        this.movie = movie;
        this.theatre = theatre;
        this.date = date;
        this.time = time;
        this.ticketPrice = ticketPrice;
    }

    public int getShowId() {
        return showId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}