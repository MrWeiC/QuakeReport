package com.example.android.quakereport;

/**
 * Created by weichen on 8/17/16.
 */
public class Earthquake {
    private String mMag;
    private String mLocation;
    private String mDate;

    public Earthquake(String mMag,String mLocation, String mDate){
        this.mMag=mMag;
        this.mLocation=mLocation;
        this.mDate=mDate;
    }


    public String getmMag() {
        return mMag;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmDate() {
        return mDate;
    }
}
