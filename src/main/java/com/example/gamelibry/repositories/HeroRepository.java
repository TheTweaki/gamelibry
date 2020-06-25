package com.example.gamelibry.repositories;

import com.example.gamelibry.model.Hero;
import com.example.gamelibry.model.Publisher;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface HeroRepository extends CrudRepository<Hero, Long> {


    Optional<Hero> getHeroByName(String name);
}