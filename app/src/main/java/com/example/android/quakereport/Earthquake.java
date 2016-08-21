package com.example.android.quakereport;

/**
 * Created by weichen on 8/17/16.
 */
public class Earthquake {
    private double mMag;
    private String mLocation;
    private long mTimeInMilliseconds;
    private String mUrl;

    public Earthquake(double mMag,String mLocation, long mTimeInMilliseconds, String mUrl){
        this.mMag=mMag;
        this.mLocation=mLocation;
        this.mTimeInMilliseconds = mTimeInMilliseconds;
        this.mUrl = mUrl;
    }


    public String getmUrl() {
        return mUrl;
    }

    public double getmMag() {
        return mMag;

    }

    public String getmLocation() {
        return mLocation;
    }

    public long getmTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }


}
