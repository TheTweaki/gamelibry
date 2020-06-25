package com.example.gamelibry.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class GameCommand {
    private Long id;
    private String title;
    private String genre;

    private String year;
    private Long publisherId;
    private Long creatorId;
    private Long heroId;
}