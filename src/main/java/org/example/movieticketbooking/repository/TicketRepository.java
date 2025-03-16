package org.example.movieticketbooking.repository;

import org.example.movieticketbooking.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    //  validate we cannot order the same sit more than once
    Optional<Ticket> findByShowtimeIdAndSeatNumber(Long showtimeId, int seatNumber);

    int countByShowtimeId(Long showtimeId);
}
