package com.matthewRiddell.FootballTeamsApi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matthewRiddell.FootballTeamsApi.model.FootballTeam;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FootballTeamsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //TODO: Add more tests

    //TODO: Fix mapper issue, local date is not converting into a string in the format expected
    @Test
    @Disabled
    void postNewTeamAndRetrieveItProvidingName() throws Exception {

        //Given
        FootballTeam northUnited = FootballTeam.builder().name("North_United").city("Newcastle Upon Tyne")
                .owner("Mike Cashley").competition("Premier League").numberOfPlayers(27)
                .stadiumCapacity(52305).dateOfCreation(LocalDate.of(1892, 12, 9)).build();

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(northUnited);

        //When
        mockMvc.perform(post("/v1/football-teams").contentType("application/json").content(json))
                .andDo(print()).andExpect(status().isOk());

        //Then
        mockMvc.perform(get("/v1/football-teams/north United")).andDo(print()).andExpect(status().isOk());
    }

}