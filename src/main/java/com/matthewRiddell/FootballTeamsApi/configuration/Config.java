package com.matthewRiddell.FootballTeamsApi.configuration;

import com.matthewRiddell.FootballTeamsApi.validator.ApiValidator;
import com.matthewRiddell.FootballTeamsApi.validator.FootballTeamValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public ApiValidator apiValidator(){
        return new ApiValidator();
    }

    @Bean
    public FootballTeamValidator footballTeamValidator(){
        return new FootballTeamValidator();
    }
}
