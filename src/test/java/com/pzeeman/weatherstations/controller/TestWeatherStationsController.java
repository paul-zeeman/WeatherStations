package com.pzeeman.weatherstations.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.pzeeman.weatherstations.service.WeatherStationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TestWeatherStationsController {

    @Autowired
    WeatherStationsController weatherStationsController;

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext wac;

    @Autowired
    private WeatherStationService service;

    @Before
    public void setup(){

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in webapp-mode (same config as spring-boot)
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void given_whenRequestingStationsPage_thenStatusIsOK() throws Exception {
        this.mockMvc.perform(get("/stations")).andExpect(status().isOk());
    }

    @Test
    public void givenExpectedStationName_whenRequestingDetailsPage_thenStatusIsOK() throws Exception {
        this.mockMvc.perform(get("/details")
                                .param("name","UNIVERSITY OF ALBERTA METABOLIC CENTRE"))
                                .andExpect(status().isOk());
    }

    @Test
    public void givenDates_whenRequestingStations_thenStatusIsOK() throws Exception {
        this.mockMvc.perform(get("/filteredStations")
                .param("startDate","2017-09-01")
                .param("endDate","2020-09-04"))
                .andExpect(status().isOk());
    }

    @Test
    public void givenBadAddress_whenRequestingPage_thenStatusIsNotFound() throws Exception {
        this.mockMvc.perform(get("/geddy")).andExpect(status().isNotFound());
    }

    /**
     * @TODO Write some failure path test cases
     */
}