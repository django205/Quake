package com.yogeshkumar.quake;

/**
 * Created by Yogesh Kumar on 4/16/2017.
 */

public class Quackdata {

    Double magnitude;
    String place;
    long time;

    public Quackdata(){}

    public Quackdata(Double magnitude, String place, long time) {
        this.magnitude = magnitude;
        this.place = place;
        this.time = time;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
