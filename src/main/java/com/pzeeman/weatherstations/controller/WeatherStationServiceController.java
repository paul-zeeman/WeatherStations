package com.pzeeman.weatherstations.controller;

import com.pzeeman.weatherstations.model.Station;
import com.pzeeman.weatherstations.service.WeatherStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class WeatherStationServiceController {

    @Autowired
    WeatherStationService weatherStationService;

    @RequestMapping(value="/stations", method = RequestMethod.GET)
    public ModelAndView getStations() {
        ModelAndView modelAndView = new ModelAndView();
        List<Station> stationList = weatherStationService.getStations();
        modelAndView.setViewName("stations");
        modelAndView.addObject("stationList", stationList);
        return modelAndView;
    }
}
