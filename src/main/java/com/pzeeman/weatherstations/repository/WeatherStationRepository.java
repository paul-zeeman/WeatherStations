package com.pzeeman.weatherstations.repository;

import com.pzeeman.weatherstations.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class WeatherStationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public List<Station> getStations() {
        return jdbcTemplate.query("select * from STATIONS", new WeatherStationRowMapper());
    }

    @Transactional
    public Station getStation(String station_name) {
        Station returnStation = new Station();
         List<Station> matchingStations =  jdbcTemplate.query( "select * FROM STATIONS where Station_Name='"+station_name+"'", new WeatherStationRowMapper());
         if (matchingStations.size() == 1)
             returnStation = matchingStations.get(0);
         return returnStation;
    }
}
