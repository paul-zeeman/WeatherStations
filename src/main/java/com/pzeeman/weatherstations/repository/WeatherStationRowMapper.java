package com.pzeeman.weatherstations.repository;

import com.pzeeman.weatherstations.model.Station;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WeatherStationRowMapper implements RowMapper<Station> {

    @Override
    public Station mapRow(ResultSet resultSet, int i) throws SQLException {
        Station station = new Station();
        station.setStation_Name(resultSet.getString("STATION_NAME"));
        station.setProvince(resultSet.getString("PROVINCE"));
        station.setStation_Date(resultSet.getString("STATION_DATE"));
        station.setMean_Temp(resultSet.getString("MEAN_TEMP"));
        station.setHighest_Monthly_Maxi_Temp(resultSet.getString("HIGHEST_MONTHLY_MAXI_TEMP"));
        station.setLowest_Monthly_Min_Temp(resultSet.getString("LOWEST_MONTHLY_MIN_TEMP"));
        return station;
    }
}
