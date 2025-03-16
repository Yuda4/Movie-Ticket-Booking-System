package org.example.movieticketbooking.service;

import org.example.movieticketbooking.model.Ticket;
import org.example.movieticketbooking.model.Showtime;
import org.example.movieticketbooking.model.User;
import org.example.movieticketbooking.repository.ShowtimeRepository;
import org.example.movieticketbooking.repository.TicketRepository;
import org.example.movieticketbooking.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketServiceTest {
    private TicketRepository ticketRepository;
    private TicketService ticketService;
    private UserRepository userRepository;
    private ShowtimeRepository showtimeRepository;

    @BeforeEach
    void setUp() {
        ticketRepository = mock(TicketRepository.class);
        userRepository = mock(UserRepository.class);
        showtimeRepository = mock(ShowtimeRepository.class);
        ticketService = new TicketService(ticketRepository);
    }

    @Test
    void bookTicket_seatAvailable_success() {
        Showtime showtime = new Showtime();
        showtime.setId(1L);  // Ensure Showtime has an ID
        showtimeRepository.save(showtime);

        User user = new User();
        user.setId(1L);
        userRepository.save(user);

        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setShowtime(showtime);  // Ensure Ticket has a valid Showtime
        ticket.setUser(user);  // Ensure Ticket has a valid Showtime

        when(ticketRepository.countByShowtimeId(1L)).thenReturn(2);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket bookedTicket = ticketService.bookTicket(ticket);

        assertNotNull(bookedTicket);
        assertEquals(1L, bookedTicket.getShowtime().getId());
    }


    @Test
    void bookTicket_seatAlreadyTaken_throwsException() {
        Showtime showtime = new Showtime();
        showtime.setId(1L);  // Ensure Showtime has an ID

        Ticket ticket = new Ticket();
        ticket.setId(2L);
        ticket.setShowtime(showtime);  // Ensure Ticket has a valid Showtime

        when(ticketRepository.countByShowtimeId(1L)).thenReturn(130); // Assume max seats are taken

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketService.bookTicket(ticket);
        });

        assertEquals("No available seats for this showtime.", exception.getMessage());
    }
}
