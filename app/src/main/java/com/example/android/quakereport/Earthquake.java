package com.example.android.quakereport;

/**
 * Created by weichen on 8/17/16.
 */
public class Earthquake {
    private double mMag;
    private String mLocation;
    private long mTimeInMilliseconds;

    public Earthquake(double mMag,String mLocation, long mTimeInMilliseconds){
        this.mMag=mMag;
        this.mLocation=mLocation;
        this.mTimeInMilliseconds = mTimeInMilliseconds;
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
