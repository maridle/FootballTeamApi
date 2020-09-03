package com.matthewRiddell.FootballTeamsApi.validator;

import com.matthewRiddell.FootballTeamsApi.exception.InvalidFootballTeamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

import java.util.Map;

public class ApiValidator {

    @Autowired
    ApplicationContext applicationContext;

    public boolean validateObject(Object object) {

        Map<String, Validator> validatorMap =
                applicationContext.getBeansOfType(Validator.class);
        if (validatorMap.isEmpty()) {
            return true;
        }

        DataBinder binder = new DataBinder(object);


        for (Validator validator : validatorMap.values()) {

            if (validator.supports(object.getClass())) {

                binder.addValidators(validator);
            }
        }
        binder.validate();
        BindingResult bindingResult = binder.getBindingResult();

        if (bindingResult.hasErrors()) {
            throw new InvalidFootballTeamException(bindingResult);
        }
        return true;
    }
}

