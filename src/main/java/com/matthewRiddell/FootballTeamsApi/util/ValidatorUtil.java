package com.matthewRiddell.FootballTeamsApi.util;

public class ValidatorUtil {

    public boolean invalidString(String input) {
        return (input.trim().length() == 0 || input.isEmpty());
    }
}
