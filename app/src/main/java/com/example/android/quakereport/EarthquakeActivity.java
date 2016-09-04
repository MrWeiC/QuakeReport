/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    /**
     * URL for earthquake data from the USGS dataset
     */
    public static final String USGS_REQUEST_URL = "http://earthquake.usgs" +
        ".gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6" +
        "&limit=10";
    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    public List<Earthquake> earthquakes = new ArrayList<>();
    private EarthquakeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        // Create a Async tasks to query the list of earthquake locations.
        QueryEarthquakeAsyncTask task = new QueryEarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        mAdapter = new EarthquakeAdapter(this, earthquakes);
        // earthquakeListView.setEmptyView(findViewById(R.id.empty_list_view));
        earthquakeListView.setAdapter(mAdapter);

        //Set the listview lister that to monitor click
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake earthquake = (Earthquake) parent.getItemAtPosition(position);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(earthquake.getmUrl()));
                startActivity(browserIntent);
            }
        });

    }


    /**
     * Async task for the query for earthquake
     */
    public class QueryEarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>> {

        @Override
        protected List<Earthquake> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null){
                return null;
            }
            earthquakes = QueryUtils.fetchEarthquakeData(urls[0]);

            return earthquakes;
        }

        @Override
        protected void onPostExecute(List<Earthquake> earthquakes) {
            if (earthquakes == null) {
                return;
            }
            Log.v("EarthQuakeActivity", "Start update ui");
            updateUi(earthquakes);
        }
    }


    /**
     * To update ui in the asynctask
     *
     * @param earthquakes
     */
    private void updateUi(List<Earthquake> earthquakes) {
        mAdapter.clear();
        if (earthquakes != null) {
            mAdapter.addAll(earthquakes);
            mAdapter.notifyDataSetChanged();
        }
    }
}
