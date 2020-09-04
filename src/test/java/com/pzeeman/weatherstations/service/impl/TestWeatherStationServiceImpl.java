package com.pzeeman.weatherstations.service.impl;

import com.pzeeman.weatherstations.model.Station;
import com.pzeeman.weatherstations.repository.WeatherStationRepository;
import com.pzeeman.weatherstations.service.WeatherStationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class TestWeatherStationServiceImpl {

    @TestConfiguration
    static class WeatherStationServiceImplTestContextConfiguration {

        @Bean
        public WeatherStationService weatherStationService() {
            return new WeatherStationServiceImpl();
        }
    }

    @Autowired
    private WeatherStationService weatherStationService;

    @MockBean
    private WeatherStationRepository weatherStationRepository;

    Station mockStation = new Station();

    @Before
    public void setUp() {

        mockStation.setStation_Name("SKYDOME");
        mockStation.setStation_Date("01/01/2018");

        List<Station> mockStationList = new ArrayList<>();
        mockStationList.add(mockStation);

        Mockito.when(weatherStationRepository.getStations())
                .thenReturn(mockStationList);

        Mockito.when(weatherStationRepository.getStation(any(String.class))).thenReturn(mockStation);

    }

    @Test
    public void whenGettingAllStation_thenStationListIsReturned() {

        List<Station> stations = weatherStationService.getStations();

        assertTrue(!stations.isEmpty());
    }
    @Test
    public void whenGettingAStation_thenAStationIsReturned() {

        Station station = weatherStationService.getStation("SKYDOME");

        assertEquals(mockStation, station);
    }
    @Test
    public void whenGettingStationsByDate_thenStationListsReturned() {

        List<Station> stations = weatherStationService.getStationsInDates("2020-09-01","2020-09-04");

        assertTrue(stations.isEmpty());
    }

    /**
     * @TODO Add tests for failure paths
     */


}
