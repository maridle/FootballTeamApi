package com.matthewRiddell.FootballTeamsApi.exception;

public class FootballTeamAlreadyExistsException extends RuntimeException {

    private String name;

    public FootballTeamAlreadyExistsException(String name) {
        this.name = name;
    }

    public String toString() {
        return "Football Team with name: " + name + " already exists";
    }
}
