package com.example.gamelibry.tools;

import com.example.gamelibry.model.Creator;
import com.example.gamelibry.model.Hero;
import com.example.gamelibry.model.Publisher;
import com.example.gamelibry.model.Game;
import com.example.gamelibry.repositories.CreatorRepository;
import com.example.gamelibry.repositories.HeroRepository;
import com.example.gamelibry.repositories.PublisherRepository;
import com.example.gamelibry.repositories.GameRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DBInflater implements ApplicationListener<ContextRefreshedEvent> {

    public DBInflater(GameRepository gameRepository, CreatorRepository creatorRepository,
                      PublisherRepository publisherRepository, HeroRepository heroRepository) {
        this.gameRepository = gameRepository;
        this.creatorRepository = creatorRepository;
        this.publisherRepository = publisherRepository;
        this.heroRepository = heroRepository;

    }

    private GameRepository gameRepository;
    private CreatorRepository creatorRepository;
    private PublisherRepository publisherRepository;
    private HeroRepository heroRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {



        Creator michal = new Creator("Michał", "Dobrowolski", "BoB");
        Publisher cdp = new Publisher("CD Project Red");

        Hero hr = new Hero("Geralt","Z rivii spoko ziomek");
        Game w3 = new Game("Wiedźmin 3", "Gra przygodowa", "2015", cdp,hr);
        michal.getGames().add(w3);
        w3.getCreators().add(michal);

        publisherRepository.save(cdp);
        heroRepository.save(hr);
        creatorRepository.save(michal);
        gameRepository.save(w3);




        Hero hrr = new Hero("Geralt","Z rivii spoko ziomek, zabójca potworów");
        Game w33 = new Game("Wiedźmin 2", "Gra przygodowa", "2012", cdp,hrr);
        michal.getGames().add(w33);
        w33.getCreators().add(michal);

        publisherRepository.save(cdp);
        heroRepository.save(hrr);
        creatorRepository.save(michal);
        gameRepository.save(w33);















    }
}