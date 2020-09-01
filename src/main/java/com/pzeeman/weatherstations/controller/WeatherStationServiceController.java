package com.pzeeman.weatherstations.controller;

import com.pzeeman.weatherstations.model.Station;
import com.pzeeman.weatherstations.service.WeatherStationService;
import com.pzeeman.weatherstations.utils.StationsByDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class WeatherStationServiceController {

    @Autowired
    WeatherStationService weatherStationService;

    @RequestMapping(value="/stations", method = RequestMethod.GET)
    public ModelAndView getStations(@RequestParam(required = false) Map<String, String> allParams) {
        ModelAndView modelAndView = new ModelAndView();
        List<Station> stationList = weatherStationService.getStations();
        if (allParams.size() == 2) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDateObject = LocalDate.parse(allParams.get("startDate"), dateTimeFormatter);
            LocalDate endDateObject = LocalDate.parse(allParams.get("endDate"), dateTimeFormatter);

            stationList.stream().filter(s -> (s.getDateObject().isEqual(startDateObject) ||
                    s.getDateObject().isEqual(endDateObject)) ||
                    (s.getDateObject().isAfter(startDateObject) && s.getDateObject().isBefore(endDateObject)))
                    .collect(Collectors.toList());
        }
        modelAndView.addObject("stationList", stationList);
        return modelAndView;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ModelAndView getDetails(@RequestParam("name") String station_name) {
        ModelAndView modelAndView = new ModelAndView();
        Station station = weatherStationService.getStation(station_name);
        modelAndView.setViewName("details");
        modelAndView.addObject("station", station);
        return modelAndView;
    }


}
