package org.example.movieticketbooking.service;

import org.example.movieticketbooking.model.Showtime;
import org.example.movieticketbooking.repository.ShowtimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;

    public ShowtimeService(ShowtimeRepository showtimeRepository) {
        this.showtimeRepository = showtimeRepository;
    }

    public Showtime addShowtime(Showtime showtime) {
        List<Showtime> overlappingShowtimes = showtimeRepository.findByTheaterAndStartTimeBetween(
                showtime.getTheater(),
                showtime.getStartTime(),
                showtime.getEndTime()
        );

        if (!overlappingShowtimes.isEmpty()) {
            throw new IllegalArgumentException("Showtime overlaps with another showtime in the same theater.");
        }
        return showtimeRepository.save(showtime);
    }

    public List<Showtime> getAllShowtimes() {
        return showtimeRepository.findAll();
    }
}
