package com.matthewRiddell.FootballTeamsApi.service;

import com.matthewRiddell.FootballTeamsApi.model.FootballTeam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class FootballTeamsServiceTest {

    @Autowired
    private FootballTeamsService footballTeamsService;

    @Test
    void duplicateFootballTeamShouldThrowFootballTeamAlreadyExistsException() {

        //Given
        FootballTeam footballTeam = FootballTeam.builder().name("Newcastle United").city("Newcastle Upon Tyne")
                .owner("Mike Cashley").competition("Premier League").numberOfPlayers(27)
                .stadiumCapacity(52305).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        footballTeamsService.createNewFootballTeam(footballTeam);

        //When
        Throwable throwable = catchThrowable(() -> footballTeamsService.createNewFootballTeam(footballTeam));

        //Then
        assertThat(throwable.toString()).contains("football team already exists");
    }

    @Test
    void footballTeamsShouldBeInOrderOfStadiumCapacity(){

        //Given
        FootballTeam newcastleUnited = FootballTeam.builder().name("Newcastle United").city("Newcastle Upon Tyne")
                .owner("Mike Cashley").competition("Premier League").numberOfPlayers(27)
                .stadiumCapacity(52305).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        FootballTeam smallFootballTeam = FootballTeam.builder().name("Small United").city("little place")
                .owner("Mike Cashless").competition("imaginary league").numberOfPlayers(14)
                .stadiumCapacity(12).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        FootballTeam mediumFootballTeam = FootballTeam.builder().name("Medium United").city("Mid place")
                .owner("Mike Money").competition("first").numberOfPlayers(18)
                .stadiumCapacity(1200).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        footballTeamsService.createNewFootballTeam(newcastleUnited);
        footballTeamsService.createNewFootballTeam(smallFootballTeam);
        footballTeamsService.createNewFootballTeam(mediumFootballTeam);

        //When
        ArrayList<FootballTeam> allFootballTeamsOrderedByCapacity = footballTeamsService.getAllFootballTeamsOrderedByCapacity();

        //Then
        assertThat(allFootballTeamsOrderedByCapacity.get(0)).isEqualTo(newcastleUnited);
        assertThat(allFootballTeamsOrderedByCapacity.get(1)).isEqualTo(mediumFootballTeam);
        assertThat(allFootballTeamsOrderedByCapacity.get(2)).isEqualTo(smallFootballTeam);

    }

    @Test
    void getAllFootBallTeamsShouldContainAllFootballTeams(){

        //Given
        FootballTeam newcastleUnited = FootballTeam.builder().name("Newcastle United").city("Newcastle Upon Tyne")
                .owner("Mike Cashley").competition("Premier League").numberOfPlayers(27)
                .stadiumCapacity(52305).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        FootballTeam smallFootballTeam = FootballTeam.builder().name("Small United").city("little place")
                .owner("Mike Cashless").competition("imaginary league").numberOfPlayers(14)
                .stadiumCapacity(12).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        FootballTeam mediumFootballTeam = FootballTeam.builder().name("Medium United").city("Mid place")
                .owner("Mike Money").competition("first").numberOfPlayers(18)
                .stadiumCapacity(1200).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        footballTeamsService.createNewFootballTeam(newcastleUnited);
        footballTeamsService.createNewFootballTeam(smallFootballTeam);
        footballTeamsService.createNewFootballTeam(mediumFootballTeam);

        //When
        final Set<FootballTeam> allFootballTeams = footballTeamsService.getAllFootballTeams();

        //Then
        assertThat(allFootballTeams).contains(newcastleUnited);
        assertThat(allFootballTeams).contains(smallFootballTeam);
        assertThat(allFootballTeams).contains(mediumFootballTeam);

    }

    @Test
    void getFootballTeamByNameShouldFindMatchingTeamGivenValidName(){

        //Given
        FootballTeam newcastleUnited = FootballTeam.builder().name("Newcastle United").city("Newcastle Upon Tyne")
                .owner("Mike Cashley").competition("Premier League").numberOfPlayers(27)
                .stadiumCapacity(52305).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        //When
        FootballTeam teamFound = footballTeamsService.getFootballTeamByName("Newcastle United");

        //Then
        assertThat(teamFound).isEqualTo(newcastleUnited);
    }

    @Test
    void getFootballTeamByNameShouldThroughNoFootballTeamFoundExceptionIfInvalidNameIsGiven(){

        //When
        Throwable throwable = catchThrowable(() -> footballTeamsService.getFootballTeamByName("random team"));

        assertThat(throwable.toString()).contains("Football team not found");
    }
}