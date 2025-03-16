package org.example.movieticketbooking.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private int duration; // Minutes

    @Column(nullable = false)
    private String rating;

    @Column(nullable = false)
    private int releaseYear;
}
