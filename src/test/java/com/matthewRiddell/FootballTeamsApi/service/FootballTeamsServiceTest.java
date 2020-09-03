package com.matthewRiddell.FootballTeamsApi.service;

import com.matthewRiddell.FootballTeamsApi.model.FootballTeam;
import com.matthewRiddell.FootballTeamsApi.repository.FootballTeamsRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@SpringBootTest
class FootballTeamsServiceTest {

    @Autowired
    private FootballTeamsService footballTeamsService;

    @Mock
    private FootballTeamsRepo footballTeamsRepo;

    @Test
    void duplicateFootballTeamShouldThrowFootballTeamAlreadyExistsException() {

        //Given
        FootballTeam footballTeam = FootballTeam.builder().name("Newcastle_United").city("Newcastle Upon Tyne")
                .owner("Mike Cashley").competition("Premier League").numberOfPlayers(27)
                .stadiumCapacity(52305).dateOfCreation(LocalDate.of(1892, 12, 9)).build();


        footballTeamsService.createNewFootballTeam(footballTeam);

        //When
        Throwable throwable = catchThrowable(() -> footballTeamsService.createNewFootballTeam(footballTeam));

        //Then
        assertThat(throwable.toString()).contains("Football Team with name: Newcastle_United already exists");
    }

    //TODO fix test when database is implemented for testing, so that the transactions can be dropped (data removed) after each test.
    @Test
    @Disabled
    void footballTeamsShouldBeInOrderOfStadiumCapacity(){

        //Given
        FootballTeam smallFootballTeam = FootballTeam.builder().name("Small United").city("little place")
                .owner("Mike Cashless").competition("imaginary league").numberOfPlayers(14)
                .stadiumCapacity(12).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        FootballTeam mediumFootballTeam = FootballTeam.builder().name("Medium United").city("Mid place")
                .owner("Mike Money").competition("first").numberOfPlayers(18)
                .stadiumCapacity(1200).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        FootballTeam footballTeam = FootballTeam.builder().name("Newcastle_United").city("Newcastle Upon Tyne")
                .owner("Mike Cashley").competition("Premier League").numberOfPlayers(27)
                .stadiumCapacity(52305).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        footballTeamsService.createNewFootballTeam(footballTeam);
        footballTeamsService.createNewFootballTeam(smallFootballTeam);
        footballTeamsService.createNewFootballTeam(mediumFootballTeam);

        //When
        ArrayList<FootballTeam> allFootballTeamsOrderedByCapacity = footballTeamsService.getAllFootballTeamsOrderedByCapacity();

        //Then
        assertThat(allFootballTeamsOrderedByCapacity.size()).isEqualTo(footballTeam);
        assertThat(allFootballTeamsOrderedByCapacity.get(1)).isEqualTo(mediumFootballTeam);
        assertThat(allFootballTeamsOrderedByCapacity.get(2)).isEqualTo(smallFootballTeam);

    }

    //TODO fix test when database is implemented for testing, so that the transactions can be dropped (data removed) after each test.
    @Test
    @Disabled
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
        final ArrayList<FootballTeam> allFootballTeams = footballTeamsService.getAllFootballTeams();

        //Then
        assertThat(allFootballTeams).contains(newcastleUnited);
        assertThat(allFootballTeams).contains(smallFootballTeam);
        assertThat(allFootballTeams).contains(mediumFootballTeam);

    }

    @Test
    void getFootballTeamByNameShouldFindMatchingTeamGivenValidName(){

        //Given
        FootballTeam someoneUnited = FootballTeam.builder().name("Someone_United").city("Newcastle Upon Tyne")
                .owner("Mike Cashley").competition("Premier League").numberOfPlayers(27)
                .stadiumCapacity(52305).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        footballTeamsService.createNewFootballTeam(someoneUnited);

        //When
        FootballTeam teamFound = footballTeamsService.getFootballTeamByName("Someone_United");

        //Then
        assertThat(teamFound).isEqualTo(someoneUnited);
    }

    @Test
    void getFootballTeamByNameShouldThroughNoFootballTeamFoundExceptionIfInvalidNameIsGiven(){

        //When
        Throwable throwable = catchThrowable(() -> footballTeamsService.getFootballTeamByName("random team"));

        assertThat(throwable.toString()).contains("could not be found");
    }
}