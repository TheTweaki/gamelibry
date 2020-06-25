package com.example.gamelibry.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@EqualsAndHashCode(of = {"id"})
@Entity
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String about;


    public Hero() {
    }
    public Hero(String name) {
        this.name = name;
    }
    public Hero(String name, String about) {
        this.name = name;
        this.about = about;

    }

}