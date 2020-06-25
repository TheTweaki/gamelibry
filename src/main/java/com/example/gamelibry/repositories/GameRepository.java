package com.example.gamelibry.repositories;

import com.example.gamelibry.model.Creator;
import com.example.gamelibry.model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GameRepository extends CrudRepository<Game, Long> {

    List<Game> getAllByCreatorsIsContaining(Creator creator);
}