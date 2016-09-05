package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weichen on 9/4/16.
 */
public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>>  {
    public String mUrl;
    public List<Earthquake> earthquakes = new ArrayList<>();

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if (mUrl == null){
            return null;
        }
        earthquakes = QueryUtils.fetchEarthquakeData(mUrl);

        return earthquakes;

    }


}
