package com.example.gamelibry.converters;

import com.example.gamelibry.commands.CreatorCommand;
import com.example.gamelibry.model.Creator;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class CreatorCommandToCreator implements Converter<CreatorCommand, Creator> {

    @Synchronized
    @Nullable
    @Override
    public Creator convert(CreatorCommand source) {
        if (source == null) {
            return null;
        }

        final Creator creator = new Creator();
        creator.setFirstName(source.getFirstName());
        creator.setLastName(source.getLastName());
        creator.setNick(source.getNick());

        return creator;
    }
}