package com.pzeeman.weatherstations.controller;

import com.pzeeman.weatherstations.model.Station;
import com.pzeeman.weatherstations.service.WeatherStationService;
import com.pzeeman.weatherstations.utils.StationsByDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class WeatherStationServiceController {

    @Autowired
    WeatherStationService weatherStationService;

    @CrossOrigin
    @RequestMapping(value="/stations", method = RequestMethod.GET)
    public String getStations(Model model) {
        List<Station> stationList = weatherStationService.getStations();
        System.out.println("Total Number of Stations " + stationList.size());
        model.addAttribute("stationList", stationList);
        return "stations";
    }

    @RequestMapping(value="/filteredStations", method = RequestMethod.GET)
    public String getFilteredStations(@RequestParam(required = true) Map<String, String> allParams, Model model) {
        List<Station> stationList = weatherStationService.getStations();
        if (allParams.size() == 2) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDateObject = LocalDate.parse(allParams.get("startDate"), dateTimeFormatter);
            LocalDate endDateObject = LocalDate.parse(allParams.get("endDate"), dateTimeFormatter);

            stationList = stationList.stream().filter(s -> (s.getDateObject().isEqual(startDateObject) ||
                    s.getDateObject().isEqual(endDateObject)) ||
                    (s.getDateObject().isAfter(startDateObject) && s.getDateObject().isBefore(endDateObject)))
                    .collect(Collectors.toList());
        }
        System.out.println("Number of filtered stations " + stationList.size());
        model.addAttribute("stationList", stationList);
        return "stations :: #stationTable";
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
