package com.movieticket.service;

import com.movieticket.model.Movie;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieServiceTest {

    @Test
    void searchMovieByTitle() {

        MovieService service = new MovieService();

        service.addMovie(
                new Movie(1, "Interstellar", "Sci-Fi", "English", 169)
        );

        List<Movie> results = service.searchMovies("Inter");

        assertEquals(1, results.size());
        assertEquals("Interstellar", results.get(0).getTitle());
    }

    @Test
    void searchIsCaseInsensitive() {

        MovieService service = new MovieService();

        service.addMovie(
                new Movie(1, "Interstellar", "Sci-Fi", "English", 169)
        );

        List<Movie> results = service.searchMovies("INTER");

        assertEquals(1, results.size());
    }

    @Test
    void searchMovieNotFound() {

        MovieService service = new MovieService();

        service.addMovie(
                new Movie(1, "Interstellar", "Sci-Fi", "English", 169)
        );

        List<Movie> results = service.searchMovies("Avatar");

        assertTrue(results.isEmpty());
    }

    @Test
    void searchWithEmptyKeyword() {

        MovieService service = new MovieService();

        service.addMovie(
                new Movie(1, "Interstellar", "Sci-Fi", "English", 169)
        );

        List<Movie> results = service.searchMovies("");

        assertTrue(results.isEmpty());
    }

    @Test
    void findMovieById() {

        MovieService service = new MovieService();

        Movie movie =
                new Movie(1, "Interstellar", "Sci-Fi", "English", 169);

        service.addMovie(movie);

        Movie result = service.findMovieById(1);

        assertNotNull(result);
        assertEquals("Interstellar", result.getTitle());
    }
}