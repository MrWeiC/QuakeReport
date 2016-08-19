package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weichen on 8/17/16.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0,earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Earthquake earthquake = getItem(position);
        View listItemView = convertView;
        if(listItemView == null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag);
        magTextView.setText(earthquake.getmMag());
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location);
        locationTextView.setText(earthquake.getmLocation());
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        dateTextView.setText(earthquake.getmDate());
        return listItemView;
    }
}
