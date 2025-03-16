package org.example.movieticketbooking.repository;

import org.example.movieticketbooking.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime, Long> {
    //    Validate there is no overlapping
    List<Showtime> findByTheaterAndStartTimeBetween(String theater, LocalDateTime start, LocalDateTime end);
}
