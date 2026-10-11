package com.movieticket.service;

import com.movieticket.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieService {

    private List<Movie> movies = new ArrayList<>();

    public void addMovie(Movie movie) {
        if (movie != null) {
            movies.add(movie);
        }
    }

    public List<Movie> searchMovies(String keyword) {

        List<Movie> results = new ArrayList<>();

        if (keyword == null || keyword.isBlank()) {
            return results;
        }

        for (Movie movie : movies) {

            if (movie.getTitle().toLowerCase()
                    .contains(keyword.toLowerCase())) {

                results.add(movie);
            }
        }

        return results;
    }

    public Movie findMovieById(int movieId) {

        for (Movie movie : movies) {

            if (movie.getMovieId() == movieId) {
                return movie;
            }
        }

        return null;
    }
}