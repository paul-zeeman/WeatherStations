package com.pzeeman.weatherstations.config;

import com.pzeeman.weatherstations.model.Station;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class TestDBLogProcessor {

    DBLogProcessor testDBLogProcessor = new DBLogProcessor();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void givenDummyStation_whenStationIsProcessed_thenLogIsCreated () throws Exception {

        Station dummyStation = new Station();
        dummyStation.setStation_Name("TEST");
        dummyStation.setStation_Date("09/04/2020");
        dummyStation.setProvince("QC");
        dummyStation.setMean_Temp("12");
        dummyStation.setLowest_Monthly_Min_Temp("-25");
        dummyStation.setHighest_Monthly_Maxi_Temp("");


        String expectedLog = "Inserting Stations : " + dummyStation.toString();

        testDBLogProcessor.process(dummyStation);

        assertTrue(outContent.toString().contains(expectedLog));
    }

}
