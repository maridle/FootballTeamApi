package com.matthewRiddell.FootballTeamsApi;

import com.matthewRiddell.FootballTeamsApi.configuration.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class FootballTeamsApiApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        SpringApplication.run(FootballTeamsApiApplication.class, args);
    }

}
