package com.example.android.quakereport;

public class EarthQuake {
    private double magnitude;
    private String place;
    private long timeInMilliSeconds;
    private String url;

    public EarthQuake(double mMagnitude,String mPlace,long mTimeInMilliSeconds,String mUrl) {
        magnitude = mMagnitude;
        place = mPlace;
        timeInMilliSeconds = mTimeInMilliSeconds;
        url=mUrl;
    }
    public double getMagnitude() {
        return magnitude;
    }
    public String getPlace() {
        return place;
    }
    public long getTimeInMilliSeconds() {
        return timeInMilliSeconds;
    }
    public  String getUrl() {return  url; }
}
