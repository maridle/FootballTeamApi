package com.matthewRiddell.FootballTeamsApi.controller;

import com.matthewRiddell.FootballTeamsApi.dto.FootballTeamCreationDto;
import com.matthewRiddell.FootballTeamsApi.mapper.FootballTeamMapper;
import com.matthewRiddell.FootballTeamsApi.model.FootballTeam;
import com.matthewRiddell.FootballTeamsApi.service.FootballTeamsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("v1")
public class FootballTeamsController {

    private Logger logger = LoggerFactory.getLogger(FootballTeamsController.class);

    private FootballTeamMapper footballTeamMapper = new FootballTeamMapper();

    @Autowired
    private FootballTeamsService footballTeamsService;

    @PostMapping("/football-teams")
    ResponseEntity<FootballTeam> newFootballTeam(@RequestBody FootballTeamCreationDto newFootballTeam) {

        logger.info("Posting a new Football Team");
        FootballTeam footballTeam = footballTeamMapper.mapFromFootballTeamDtoToFootballTeam(newFootballTeam);
        footballTeamsService.createNewFootballTeam(footballTeam);

        return ResponseEntity.ok(footballTeam);
    }

    @GetMapping("/football-teams/{teamName}")
    ResponseEntity<FootballTeam> getTeamByName(@PathVariable String teamName) {
        //TODO: add mapper to convert the team name back to a string without underscore
        logger.info("Getting a Team by name");
        return ResponseEntity.ok(footballTeamsService.getFootballTeamByName(teamName.replaceAll(" ", "_")));
    }

    @GetMapping("/football-teams")
    ResponseEntity<ArrayList<FootballTeam>> getAllTeams(@RequestParam(defaultValue = "false", required = false) boolean sortByStadiumCapacity) {

        ArrayList<FootballTeam> footballTeams;

        if (sortByStadiumCapacity) {
            logger.info("Getting all teams ordered by staduim capacity");
            footballTeams = footballTeamsService.getAllFootballTeamsOrderedByCapacity();
        } else {
            logger.info("Getting all teams");
            footballTeams = footballTeamsService.getAllFootballTeams();
        }
        return ResponseEntity.ok(footballTeams);
    }

}
