package com.pzeeman.weatherstations.config;
 
import com.pzeeman.weatherstations.model.Station;
import org.springframework.batch.item.ItemProcessor;

// https://github.com/avinash28196/SpringBatch-LoadingCSVFileToH2Database
public class DBLogProcessor implements ItemProcessor<Station, Station>
{
    public Station process(Station station) throws Exception
    {
        System.out.println("Inserting Stations : " + station);
        return station;
    }
}