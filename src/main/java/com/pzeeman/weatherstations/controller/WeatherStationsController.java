package com.pzeeman.weatherstations.controller;

import com.pzeeman.weatherstations.model.Station;
import com.pzeeman.weatherstations.service.WeatherStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class WeatherStationsController {

    @Autowired
    WeatherStationService weatherStationService;

    @CrossOrigin
    @RequestMapping(value="/stations", method = RequestMethod.GET)
    public String getStations(Model model) {
        List<Station> stationList = weatherStationService.getStations();
        model.addAttribute("stationList", stationList);
        return "stations";
    }

    @RequestMapping(value="/filteredStations", method = RequestMethod.GET)
    public String getFilteredStations(@RequestParam(required = false) Map<String, String> allParams, Model model) {
        List<Station> stationList = weatherStationService.getStationsInDates(allParams.get("startDate"),allParams.get("endDate"));
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
