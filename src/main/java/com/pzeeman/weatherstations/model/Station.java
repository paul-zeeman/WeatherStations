package com.pzeeman.weatherstations.model;

public class Station {

    String station_Name;
    String province;
    String station_Date;
    String mean_Temp;
    String highest_Monthly_Maxi_Temp;
    String lowest_Monthly_Min_Temp;

    public String getStation_Name() { return station_Name; }
    public void setStation_Name(String station_Name) { this.station_Name = station_Name; }
    public String getProvince() { return province; }
    public void setProvince(String province) {this.province = province; }
    public String getStation_Date() {
        return this.station_Date;
    }
    public void setStation_Date(String dateString) {
        this.station_Date = dateString;
    }
    public String getMean_Temp() { return mean_Temp; }
    public void setMean_Temp(String meanTempString){ this.mean_Temp = meanTempString; }
    public String getLowest_Monthly_Min_Temp() { return lowest_Monthly_Min_Temp; }
    public void setLowest_Monthly_Min_Temp(String lowestMonthlyMinimumTempString) { this.lowest_Monthly_Min_Temp = lowestMonthlyMinimumTempString;}
    public String getHighest_Monthly_Maxi_Temp() { return highest_Monthly_Maxi_Temp; }
    public void setHighest_Monthly_Maxi_Temp(String highestMonthlyMaximumTempString) {this.highest_Monthly_Maxi_Temp = highestMonthlyMaximumTempString;}

    public String toString() {
        return ("station_name= " + station_Name + ", province=" + province +", date="+ station_Date +", mean_Temp="+ mean_Temp + ", highest_Monthly_Maxi_Temp=" + highest_Monthly_Maxi_Temp + ", lowest_Monthly_Min_Temp="+ lowest_Monthly_Min_Temp);

    }
}
