package com.example.gamelibry.controllers;

import com.example.gamelibry.commands.CreatorCommand;
import com.example.gamelibry.converters.CreatorCommandToCreator;
import com.example.gamelibry.repositories.CreatorRepository;
import com.example.gamelibry.repositories.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CreatorController {

    private CreatorRepository creatorRepository;
    private GameRepository gameRepository;
    private CreatorCommandToCreator creatorCommandToCreator;

    public CreatorController(CreatorRepository creatorRepository, GameRepository gameRepository,
                             CreatorCommandToCreator creatorCommandToCreator) {
        this.creatorRepository = creatorRepository;
        this.gameRepository = gameRepository;
        this.creatorCommandToCreator = creatorCommandToCreator;
    }

    @RequestMapping(value = {"/creators", "/creator/list"})
    public String getCreator(Model model) {
        model.addAttribute("creators", creatorRepository.findAll());
        return "creator/list";
    }

    @RequestMapping("/creator/{id}/games")
    public String getCreatorGames(Model model, @PathVariable("id") Long id) {
        Optional<com.example.gamelibry.model.Creator> creator = creatorRepository.findById(id);

        if (creator.isPresent()) {
            model.addAttribute("games", gameRepository.getAllByCreatorsIsContaining(creator.get()));
            model.addAttribute("filter",
                    "creator: " + creator.get().getFirstName() + " " + creator.get().getLastName());
        } else {
            model.addAttribute("games", new ArrayList<>());
            model.addAttribute("filter", "creator for this id doesn't exist");
        }

        return "game/list";
    }

    @RequestMapping("/creator/{id}/show")
    public String getCreatorDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("creator", creatorRepository.findById(id).get());
        return "creator/show";
    }

    @RequestMapping("/creator/{id}/delete")
    public String deleteCreator(@PathVariable("id") Long id) {
        creatorRepository.deleteById(id);
        return "redirect:/creator";
    }

    @GetMapping
    @RequestMapping("/creator/new")
    public String newGame(Model model){
        model.addAttribute("creator", new CreatorCommand());
        return "creator/addedit";
    }

    @PostMapping("creator")
    public String saveOrUpdate(@ModelAttribute CreatorCommand command){

        Optional<com.example.gamelibry.model.Creator> creatorOptional =
                creatorRepository.getFirstByFirstNameAndLastName(command.getFirstName(), command.getLastName());

        if (!creatorOptional.isPresent()) {
            com.example.gamelibry.model.Creator detachedCreator = creatorCommandToCreator.convert(command);
            com.example.gamelibry.model.Creator savedCreator = creatorRepository.save(detachedCreator);
            return "redirect:/creator/" + savedCreator.getId() + "/show";
        } else {
            //TODO: error message to template
            System.out.println("Sorry, there's such creator in db");
            return "redirect:/creator/" + creatorOptional.get().getId() + "/show";
        }
    }
}