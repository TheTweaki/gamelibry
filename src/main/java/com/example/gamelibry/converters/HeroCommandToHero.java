package com.example.gamelibry.converters;

import com.example.gamelibry.commands.HeroCommand;
import com.example.gamelibry.commands.PublisherCommand;
import com.example.gamelibry.model.Hero;
import com.example.gamelibry.model.Publisher;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class HeroCommandToHero implements Converter<HeroCommand, Hero> {

    @Synchronized
    @Nullable
    @Override
    public Hero convert(HeroCommand source) {
        if (source == null) {
            return null;
        }

        final Hero hero = new Hero();
        hero.setName(source.getName());
        hero.setAbout(source.getAbout());


        return hero;
    }
}