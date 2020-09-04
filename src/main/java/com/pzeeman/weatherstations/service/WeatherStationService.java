package com.pzeeman.weatherstations.service;

import com.pzeeman.weatherstations.model.Station;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WeatherStationService {
    public abstract List<Station> getStations();

    public abstract Station getStation(String station_name);

    public abstract List<Station> getStationsInDates(String startDate, String endDate);
}
