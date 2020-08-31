package com.pzeeman.weatherstations.service.impl;

import com.pzeeman.weatherstations.model.Station;
import com.pzeeman.weatherstations.repository.WeatherStationRepository;
import com.pzeeman.weatherstations.service.WeatherStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherStationServiceImpl implements WeatherStationService {
    @Autowired
    WeatherStationRepository weatherStationRepository;

    @Override
    public List<Station> getStations() {
        return weatherStationRepository.getStations();
    }

    @Override
    public Station getStation(String station_name) { return weatherStationRepository.getStation(station_name); }
}
