package com.example.gamelibry.controllers;

import com.example.gamelibry.commands.HeroCommand;
import com.example.gamelibry.commands.PublisherCommand;
import com.example.gamelibry.converters.HeroCommandToHero;
import com.example.gamelibry.model.Hero;
import com.example.gamelibry.model.Publisher;
import com.example.gamelibry.repositories.HeroRepository;
import com.example.gamelibry.repositories.PublisherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class HeroController {

    private HeroRepository heroRepository;
    private HeroCommandToHero heroCommandToHero;

    public HeroController(HeroRepository heroRepository, HeroCommandToHero heroCommandToHero) {
        this.heroRepository = heroRepository;
        this.heroCommandToHero = heroCommandToHero;
    }

    @RequestMapping(value = {"/heroes", "/hero/list"})
    public String getHeroes(Model model) {
        model.addAttribute("heroes", heroRepository.findAll());
        return "hero/list";
    }

    @RequestMapping("/hero/{id}/show")
    public String getHeroDetails(Model model, @PathVariable("id") Long id) {
        model.addAttribute("hero", heroRepository.findById(id).get());
        return "hero/show";
    }

    @RequestMapping("/hero/{id}/delete")
    public String deleteHero(@PathVariable("id") Long id) {
        heroRepository.deleteById(id);
        return "redirect:/heroes";
    }

    @GetMapping
    @RequestMapping("/hero/new")
    public String newGame(Model model){
        model.addAttribute("hero", new HeroCommand());
        return "hero/addedit";
    }

    @PostMapping("hero")
    public String saveOrUpdate(@ModelAttribute HeroCommand command){

        Optional<Hero> heroOptional = heroRepository.getHeroByName(command.getName());

        if (!heroOptional.isPresent()) {
            Hero detachedHero = heroCommandToHero.convert(command);
            Hero savedHero = heroRepository.save(detachedHero);
            return "redirect:/hero/" + savedHero.getId() + "/show";
        } else {
            //TODO: error message to template
            System.out.println("Sorry, there's such hero in db");
            return "redirect:/hero/" + heroOptional.get().getId() + "/show";
        }
    }
}