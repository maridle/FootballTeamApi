package com.matthewRiddell.FootballTeamsApi.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class InvalidFootballTeamException extends RuntimeException {

    private BindingResult bindingResult;

    public InvalidFootballTeamException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public String toString() {
        return "Invalid " + bindingResult.getTarget() + " Caused by: " + bindingResult.getFieldErrors();
    }
}
