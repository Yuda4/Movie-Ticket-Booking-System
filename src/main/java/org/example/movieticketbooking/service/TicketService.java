package org.example.movieticketbooking.service;

import org.example.movieticketbooking.model.Ticket;
import org.example.movieticketbooking.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;


    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket bookTicket(Ticket ticket) {
        if (ticket.getShowtime() == null) {
            throw new IllegalArgumentException("Showtime cannot be null.");
        }

        Long showtimeId = ticket.getShowtime().getId();
        int bookedSeats = ticketRepository.countByShowtimeId(showtimeId);


        if (bookedSeats >= AppConst.MAX_SEATS_PER_SHOWTIME) {
            throw new IllegalArgumentException("No available seats for this showtime.");
        }

        Optional<Ticket> existingTicket = ticketRepository.findByShowtimeIdAndSeatNumber(ticket.getShowtime().getId(), ticket.getSeatNumber());
        if (existingTicket.isPresent()) {
            throw new IllegalArgumentException("Seat is already booked for this showtime.");
        }

        return ticketRepository.save(ticket);
    }

    public void cancelTicket(Long ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isEmpty()) {
            throw new IllegalArgumentException("Ticket not found.");
        }
        Ticket ticket = optionalTicket.get();

        LocalDateTime now = LocalDateTime.now();
        if (ticket.getShowtime().getStartTime().minusHours(3).isBefore(now)) {
            throw new IllegalArgumentException("Cannot cancel ticket less than 3 hours before showtime.");
        }
        ticketRepository.delete(ticket);
    }
}
