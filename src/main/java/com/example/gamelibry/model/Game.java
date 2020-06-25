package com.example.gamelibry.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String genre;
    private String year;

    @ManyToOne
    private Publisher publisher;

    @ManyToOne
    private Hero hero;



    @ManyToMany
    private Set<Creator> creators = new HashSet<>();

    public Game(String title, String genre, String year, Publisher publisher, Hero hero) {
        this.title = title;
        this.genre = genre;

        this.year = year;
        this.publisher = publisher;
        this.hero = hero;
    }
}