package com.matthewRiddell.FootballTeamsApi.mapper;

import com.matthewRiddell.FootballTeamsApi.controller.FootballTeamsController;
import com.matthewRiddell.FootballTeamsApi.dto.FootballTeamCreationDto;
import com.matthewRiddell.FootballTeamsApi.exception.DateFormatException;
import com.matthewRiddell.FootballTeamsApi.model.FootballTeam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class FootballTeamMapper {

    public Logger logger = LoggerFactory.getLogger(FootballTeamsController.class);

    private static Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    public FootballTeam mapFromFootballTeamDtoToFootballTeam(FootballTeamCreationDto footballTeamCreationDto) {

        FootballTeam newFootballTeam;

        //validate that the date has the correct format
        if (pattern.matcher(footballTeamCreationDto.getDateOfCreation()).matches()) {

            logger.info("provided football team date is valid");
            LocalDate localDate = LocalDate.parse(footballTeamCreationDto.getDateOfCreation());

            //move
            logger.info("mapping football team dto to football team");
            newFootballTeam = FootballTeam.builder()
                    .name(footballTeamCreationDto.getName().replaceAll(" ", "_"))
                    .owner(footballTeamCreationDto.getOwner())
                    .competition(footballTeamCreationDto.getCompetition())
                    .city(footballTeamCreationDto.getCity())
                    .stadiumCapacity(footballTeamCreationDto.getStadiumCapacity())
                    .numberOfPlayers(footballTeamCreationDto.getNumberOfPlayers())
                    .dateOfCreation(localDate)
                    .build();

            return newFootballTeam;

        } else {
            throw new DateFormatException(footballTeamCreationDto.getDateOfCreation());
        }

        //return newFootballTeam;

    }

}
