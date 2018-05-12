package com.example.a10010582.weatherapp;

/**
 * Created by ramas_000 on 12/15/2016.
 */

public class DailyForecast {
    int imageResource;
    String condition;
    String date;
    String day;
    String high;
    String low;
    public DailyForecast(int imageResource, String condition, String date, String day, String high, String low){
        this.imageResource = imageResource;
        this.condition = condition;
        this.date = date;
        this.day = day;
        this.high = high;
        this.low = low;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getCondition() {
        return condition;
    }

    public String getDate() {
        return date;
    }

    public String getDay() {
        return day;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }

}
