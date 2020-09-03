package com.matthewRiddell.FootballTeamsApi.validator;

import com.matthewRiddell.FootballTeamsApi.model.FootballTeam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class FootballTeamValidatorTest {

    @Autowired
    private ApiValidator apiValidator;

    @Test
    public void footballTeamWithLessThanSevenPlayersThrowsInvalidFootballTeamException(){
        //googled it and apparently you have to have a minimum of seven players to be regarded as a team

        //Given
        FootballTeam lessThanSevenPlayers = FootballTeam.builder().name("underseven").city("Newcastle Upon Tyne")
                .owner("Mike Cashley").competition("Premier League").numberOfPlayers(6)
                .stadiumCapacity(52305).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        //When
        Throwable throwable = catchThrowable(() -> apiValidator.validateObject(lessThanSevenPlayers));

        //Then
        assertThat(throwable.toString().contains("number of players under minimum of seven"));

    }

    @Test
    public void footballTeamWithEmptyValuesShouldHaveErrors(){

        //Given
        FootballTeam newcastleUnited = FootballTeam.builder().name("").city("")
                .owner("").competition("").numberOfPlayers(27)
                .stadiumCapacity(52305).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        //When
        Throwable throwable = catchThrowable(() -> apiValidator.validateObject(newcastleUnited));

        //Then
        assertThat(throwable.toString().contains("name.empty"));
        assertThat(throwable.toString().contains("owner.empty"));
        assertThat(throwable.toString().contains("city.empty"));
        assertThat(throwable.toString().contains("competition.empty"));
    }

}