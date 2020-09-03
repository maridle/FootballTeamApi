package com.matthewRiddell.FootballTeamsApi.validator;


import com.matthewRiddell.FootballTeamsApi.model.FootballTeam;
import com.matthewRiddell.FootballTeamsApi.util.ValidatorUtil;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FootballTeamValidator implements Validator {

    private ValidatorUtil validatorUtil = new ValidatorUtil();

    @Override
    public boolean supports(Class<?> object) {
        return FootballTeam.class == object;
    }

    @Override
    public void validate(Object object, Errors errors) {

        FootballTeam footballTeam = (FootballTeam) object;


        //validation (further validation can be added for a football team)
        if (validatorUtil.invalidString(footballTeam.getName())) {
            errors.rejectValue("name", "name.empty");
        }

        if (validatorUtil.invalidString(footballTeam.getName())) {
            errors.rejectValue("city", "city.empty");
        }

        if (validatorUtil.invalidString(footballTeam.getName())) {
            errors.rejectValue("owner", "owner.empty");
        }

        if (validatorUtil.invalidString(footballTeam.getName())) {
            errors.rejectValue("competition", "competition.empty");
        }

        if (footballTeam.getNumberOfPlayers() < 7) {
            errors.rejectValue("number of players", "number of players under minimum of seven");
        }
    }
}
