package com.matthewRiddell.FootballTeamsApi.repository;

import com.matthewRiddell.FootballTeamsApi.model.FootballTeam;
import com.matthewRiddell.FootballTeamsApi.validator.ApiValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

//Used to replicate a db storage of a Football Team
@Repository
public class FootballTeamsRepo {

    @Autowired
    private ApiValidator apiValidator;

    //Assumption that we have two endpoints to get (all teams) one unordered and one that is ordered by stadium capacity.
    //If it was always sorted:
    //SortedSet allTeams = new TreeSet<>(Comparator.comparing(FootballTeam::getStadiumCapacity());
    private final ArrayList<FootballTeam> allTeams = new ArrayList<>();


    public ArrayList<FootballTeam> getAllTeams() {
        return allTeams;
    }

    public Optional<FootballTeam> getFootballTeamByName(String name) {
        return allTeams.stream().filter(footballTeam -> footballTeam.getName().equals(name)).findFirst();
    }

    public void addFootballTeam(FootballTeam footballTeamToAdd) {

        //validate
        apiValidator.validateObject(footballTeamToAdd);

        //add
        allTeams.add(footballTeamToAdd);
    }
}
