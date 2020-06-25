package com.example.gamelibry.controllers;

import com.example.gamelibry.commands.GameCommand;
import com.example.gamelibry.converters.GameCommandToGame;
import com.example.gamelibry.model.Game;
import com.example.gamelibry.repositories.CreatorRepository;
import com.example.gamelibry.repositories.HeroRepository;
import com.example.gamelibry.repositories.PublisherRepository;
import com.example.gamelibry.repositories.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GameController {

    private GameRepository gameRepository;
    private GameCommandToGame gameCommandToGame;
    private PublisherRepository publisherRepository;
    private HeroRepository heroRepository;
    private CreatorRepository creatorRepository;

    public GameController(GameRepository gameRepository, GameCommandToGame gameCommandToGame,
                          PublisherRepository publisherRepository, CreatorRepository creatorRepository,
                          HeroRepository heroRepository) {
        this.gameRepository = gameRepository;
        this.gameCommandToGame = gameCommandToGame;
        this.publisherRepository = publisherRepository;
        this.creatorRepository = creatorRepository;
        this.heroRepository = heroRepository;
    }

    @GetMapping
    @RequestMapping(value = {"/games" , "game/list"})
    public String getGames(Model model) {
        model.addAttribute("games", gameRepository.findAll());
        return "game/list";
    }

    @GetMapping
    @RequestMapping("/game/{id}/show")
    public String getGameDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("game", gameRepository.findById(id).get());
        return "game/show";
    }

    @GetMapping
    @RequestMapping("/game/{id}/delete")
    public String deleteGame(@PathVariable("id") Long id) {
        gameRepository.deleteById(id);
        return "redirect:/games";
    }

    @GetMapping
    @RequestMapping("/game/new")
    public String newGame(Model model){
        model.addAttribute("game", new GameCommand());
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("heroes", heroRepository.findAll());
        model.addAttribute("creators", creatorRepository.findAll());
        return "game/addedit";
    }

    @PostMapping("game")
    public String saveOrUpdate(@ModelAttribute GameCommand command){
        Game detachedGame = gameCommandToGame.convert(command);
        Game savedGame = gameRepository.save(detachedGame);

        return "redirect:/game/" + savedGame.getId() + "/show";
    }
}