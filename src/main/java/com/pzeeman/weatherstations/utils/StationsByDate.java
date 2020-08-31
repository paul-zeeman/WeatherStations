package com.pzeeman.weatherstations.utils;

import com.pzeeman.weatherstations.model.Station;

import java.util.Comparator;

public class StationsByDate implements Comparator<Station> {
    @Override
    public int compare(Station x, Station y) {
        return x.getDateObject().compareTo(y.getDateObject());
    }
}
