package com.matthewRiddell.FootballTeamsApi.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FootballTeam {

    private String name;

    private String city;

    private String owner;

    private int stadiumCapacity;

    private String competition;

    private int numberOfPlayers;

    private LocalDate dateOfCreation;

}
