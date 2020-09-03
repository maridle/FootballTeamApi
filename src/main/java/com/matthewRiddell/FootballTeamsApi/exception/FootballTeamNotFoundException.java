package com.matthewRiddell.FootballTeamsApi.exception;

public class FootballTeamNotFoundException extends RuntimeException {

    private String name;

    public FootballTeamNotFoundException(String name) {
        this.name = name;
    }

    public String toString() {
        return "Football Team with name: " + name + " could not be found";
    }
}
