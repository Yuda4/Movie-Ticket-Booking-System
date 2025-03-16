package org.example.movieticketbooking.controller;

import org.example.movieticketbooking.model.Showtime;
import org.example.movieticketbooking.service.ShowtimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/showtimes")
public class ShowtimeController {
    private final ShowtimeService showtimeService;

    public ShowtimeController(ShowtimeService showtimeService) {
        this.showtimeService = showtimeService;
    }

    @GetMapping
    public List<Showtime> getAllShowtimes() {
        return showtimeService.getAllShowtimes();
    }

    @PostMapping
    public ResponseEntity<?> addShowtime(@RequestBody Showtime showtime) {
        try {
            return ResponseEntity.ok(showtimeService.addShowtime(showtime));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

