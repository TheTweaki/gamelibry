package com.example.gamelibry.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class HeroCommand {
    private Long id;
    private String name;
    private String about;

}