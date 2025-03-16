package org.example.movieticketbooking.controller;

import org.example.movieticketbooking.model.Movie;
import org.example.movieticketbooking.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @PostMapping
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        try {
            return ResponseEntity.ok(movieService.addMovie(movie));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
