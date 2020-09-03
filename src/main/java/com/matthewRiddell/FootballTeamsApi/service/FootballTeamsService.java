package com.matthewRiddell.FootballTeamsApi.service;

import com.matthewRiddell.FootballTeamsApi.controller.FootballTeamsController;
import com.matthewRiddell.FootballTeamsApi.exception.FootballTeamAlreadyExistsException;
import com.matthewRiddell.FootballTeamsApi.exception.FootballTeamNotFoundException;
import com.matthewRiddell.FootballTeamsApi.model.FootballTeam;
import com.matthewRiddell.FootballTeamsApi.repository.FootballTeamsRepo;
import com.matthewRiddell.FootballTeamsApi.validator.ApiValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

@Service
public class FootballTeamsService {

    public Logger logger = LoggerFactory.getLogger(FootballTeamsController.class);

    @Autowired
    private FootballTeamsRepo footballTeamsRepo;

    @Autowired
    private ApiValidator apiValidator;


    public FootballTeam getFootballTeamByName(String name) {
        return footballTeamsRepo.getFootballTeamByName(name).orElseThrow(() -> new FootballTeamNotFoundException(name));
    }

    public ArrayList<FootballTeam> getAllFootballTeams() {
        return footballTeamsRepo.getAllTeams();
    }

    public ArrayList<FootballTeam> getAllFootballTeamsOrderedByCapacity() {
        ArrayList<FootballTeam> teams = footballTeamsRepo.getAllTeams();
        teams.sort(Comparator.comparing(FootballTeam::getStadiumCapacity));
        return teams;
    }

    public void createNewFootballTeam(FootballTeam footballTeam) {

        checkIfFootballTeamWithMatchingNameAlreadyExists(footballTeam);

        logger.info("validating football team");
        apiValidator.validateObject(footballTeam);

        logger.info("persisting football team");
        footballTeamsRepo.addFootballTeam(footballTeam);
    }

    public void checkIfFootballTeamWithMatchingNameAlreadyExists(FootballTeam footballTeam) {
        logger.info("validating football team does not already exist");

        try {
            if (getFootballTeamByName(footballTeam.getName()) != null) {
                throw new FootballTeamAlreadyExistsException(footballTeam.getName());
            }
        } catch (FootballTeamNotFoundException ignored) {
            logger.info("football team not found exception ignored");
        }
    }
}
