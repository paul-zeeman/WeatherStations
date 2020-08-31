package com.pzeeman.weatherstations.model;

public class Station {

    String name;
    String province;
    String date;
    String mean_Temp;
    String highest_Monthly_Maxi_Temp;
    String lowest_Monthly_Min_Temp;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getProvince() { return province; }
    public void setProvince(String province) {this.province = province; }
    public String getDate() {
        return this.date;
    }

    public void setDate(String dateString) {
        this.date = dateString;
    }
    public String getMean_Temp() { return mean_Temp; }
    public void setMean_Temp(String meanTempString){ this.mean_Temp = meanTempString; }
    public String getLowest_Monthly_Min_Temp() { return lowest_Monthly_Min_Temp; }
    public void setLowest_Monthly_Min_Temp(String lowestMonthlyMinimumTempString) { this.lowest_Monthly_Min_Temp = lowestMonthlyMinimumTempString;}
    public String getHighest_Monthly_Maxi_Temp() { return highest_Monthly_Maxi_Temp; }
    public void setHighest_Monthly_Maxi_Temp(String highestMonthlyMaximumTempString) {this.highest_Monthly_Maxi_Temp = highestMonthlyMaximumTempString;}

    public String toString() {
        return ("name= " + name + ", province=" + province +", date="+ date +", mean_Temp="+ mean_Temp + ", highest_Monthly_Maxi_Temp=" + highest_Monthly_Maxi_Temp + ", lowest_Monthly_Min_Temp="+ lowest_Monthly_Min_Temp);

    }
}
