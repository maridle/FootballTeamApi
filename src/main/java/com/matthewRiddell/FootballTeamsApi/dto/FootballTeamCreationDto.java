package com.matthewRiddell.FootballTeamsApi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class FootballTeamCreationDto {

        private String name;

        private String city;

        private String owner;

        private int stadiumCapacity;

        private String competition;

        private int numberOfPlayers;

        private String dateOfCreation;
}
