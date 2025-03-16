package org.example.movieticketbooking.service;

import org.example.movieticketbooking.model.Showtime;
import org.example.movieticketbooking.repository.ShowtimeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class ShowtimeServiceTest {
    private ShowtimeRepository showtimeRepository;
    private ShowtimeService showtimeService;

    @BeforeEach
    void setUp() {
        showtimeRepository = mock(ShowtimeRepository.class);
        showtimeService = new ShowtimeService(showtimeRepository);
    }

    @Test
    void addShowtime_noOverlap_success() {
        Showtime showtime = new Showtime(null, null, "Theater 1", LocalDateTime.now(), LocalDateTime.now().plusHours(2));

        when(showtimeRepository.findByTheaterAndStartTimeBetween(anyString(), any(), any())).thenReturn(List.of());
        when(showtimeRepository.save(any())).thenReturn(showtime);

        Showtime savedShowtime = showtimeService.addShowtime(showtime);
        assertNotNull(savedShowtime);
    }

    @Test
    void addShowtime_withOverlap_throwsException() {
        Showtime showtime = new Showtime(null, null, "Theater 1", LocalDateTime.now(), LocalDateTime.now().plusHours(2));

        when(showtimeRepository.findByTheaterAndStartTimeBetween(anyString(), any(), any())).thenReturn(List.of(showtime));
        assertThrows(IllegalArgumentException.class, () -> showtimeService.addShowtime(showtime));
    }
}
