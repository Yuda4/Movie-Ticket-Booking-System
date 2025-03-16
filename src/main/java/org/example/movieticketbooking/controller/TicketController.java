package org.example.movieticketbooking.controller;

import org.example.movieticketbooking.model.Ticket;
import org.example.movieticketbooking.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getAllMovies() {
        return ticketService.getAllTickets();
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookTicket(@RequestBody Ticket ticket) {
        try {
            return ResponseEntity.ok(ticketService.bookTicket(ticket));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/cancel/{ticketId}")
    public ResponseEntity<?> cancelTicket(@PathVariable("ticketId") Long ticketId) {
        try {
            ticketService.cancelTicket(ticketId);
            return ResponseEntity.ok("Ticket canceled successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
