package com.example.gamelibry.converters;

import com.example.gamelibry.commands.GameCommand;
import com.example.gamelibry.model.Creator;
import com.example.gamelibry.model.Game;
import com.example.gamelibry.model.Hero;
import com.example.gamelibry.model.Publisher;
import com.example.gamelibry.repositories.CreatorRepository;
import com.example.gamelibry.repositories.HeroRepository;
import com.example.gamelibry.repositories.PublisherRepository;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class GameCommandToGame implements Converter<GameCommand, Game> {

    private PublisherRepository publisherRepository;
    private HeroRepository heroRepository;
    private CreatorRepository creatorRepository;

    public GameCommandToGame(PublisherRepository publisherRepository, CreatorRepository creatorRepository,
                             HeroRepository heroRepository) {
        this.publisherRepository = publisherRepository;
        this.creatorRepository = creatorRepository;
        this.heroRepository = heroRepository;
    }

    @Synchronized
    @Nullable
    @Override
    public Game convert(GameCommand source) {
        if (source == null) {
            return null;
        }

        final Game game = new Game();
        game.setId(source.getId());
        game.setTitle(source.getTitle());
        game.setGenre(source.getGenre());
        game.setYear(source.getYear());


        Optional<Publisher> publisher = publisherRepository.findById(source.getPublisherId());

        if (publisher.isPresent()) {
            game.setPublisher(publisher.get());
        } else {
            game.setPublisher(publisherRepository.getPublisherByName("Unknown").get());
        }

        Optional<Hero> hero = heroRepository.findById(source.getHeroId());

        if (hero.isPresent()) {
            game.setHero(hero.get());
        } else {
            game.setHero(heroRepository.getHeroByName("Unknown").get());
        }

        Optional<Creator> creator = creatorRepository.findById(source.getCreatorId());

        if (creator.isPresent()) {
            game.getCreators().add(creator.get());
        }

        return game;
    }
}