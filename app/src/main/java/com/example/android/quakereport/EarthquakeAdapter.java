package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by weichen on 8/17/16.
 */
public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOG_TAG = EarthquakeAdapter.class.getSimpleName();

    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Earthquake earthquake = getItem(position);
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,
                false);
        }

        TextView magTextView = (TextView) listItemView.findViewById(R.id.mag);
        //use decimal formatter to reformat the double to display as we expected.
        DecimalFormat magFormatter = new DecimalFormat("0.0");
        magTextView.setText(magFormatter.format(earthquake.getmMag()));

        //Create a Date to display the date
        Date dateObject = new Date(earthquake.getmTimeInMilliseconds());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);

        dateTextView.setText(formatDate(dateObject));
        timeTextView.setText(formatTime(dateObject));

        //Create TextView to display primary and offset string
        TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id
            .primary_location);
        TextView offsetTextView = (TextView) listItemView.findViewById(R.id.offset);
        primaryLocationTextView.setText(getPrimaryLocation(earthquake.getmLocation()));
        offsetTextView.setText(getOffset(earthquake.getmLocation()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquake.getmMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return listItemView;
    }

    private int getMagnitudeColor(double mag) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int)Math.floor(mag);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;

        }
        //convert int to color code.
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

    private String getOffset(String location) {
        if (location.contains("of"))
            return location.substring(0, location.indexOf("of") + 3);
        else{
            return "Near the";
        }
    }

    private String getPrimaryLocation(String location) {
        if (location.contains("of"))
            return location.substring(location.indexOf("of") + 3, location.length());
        else{
            return location;
        }
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat date = new SimpleDateFormat("LLL dd, yyyy");
        return date.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat time = new SimpleDateFormat("h:mm a");
        return time.format(dateObject);
    }
}
