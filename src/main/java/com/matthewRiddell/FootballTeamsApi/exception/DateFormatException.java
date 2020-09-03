package com.matthewRiddell.FootballTeamsApi.exception;

public class DateFormatException extends RuntimeException {

    private String date;

    public DateFormatException(String date) {
        this.date = date;
    }

    public String toString() {
        return "Invalid Date Format, date " + date + " does not match YYYY/MM/DD";
    }
}
