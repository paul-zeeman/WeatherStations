package com.pzeeman.weatherstations.service.impl;

import com.pzeeman.weatherstations.model.Station;
import com.pzeeman.weatherstations.repository.WeatherStationRepository;
import com.pzeeman.weatherstations.service.WeatherStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Station> getStationsInDates(String startDate, String endDate) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDateObject = LocalDate.now();
        LocalDate endDateObject = LocalDate.now();
        try {
             startDateObject = LocalDate.parse(startDate, dateTimeFormatter);
             endDateObject = LocalDate.parse(endDate, dateTimeFormatter);
        } catch (DateTimeParseException dtpe) {
            // If we can't parse the dates, just give back the unfiltered list
            return getStations();
        }
        final LocalDate finalStartDateObject = startDateObject;
        final LocalDate finalEndDateObject = endDateObject;

        // Return the list of stations that are between the start date and end date inclusively.
        return  weatherStationRepository.getStations().stream().filter(s -> (s.getDateObject().isEqual( finalStartDateObject) ||
                s.getDateObject().isEqual(finalEndDateObject)) ||
                (s.getDateObject().isAfter(finalStartDateObject) && s.getDateObject().isBefore(finalEndDateObject)))
                .collect(Collectors.toList());
    }
}
