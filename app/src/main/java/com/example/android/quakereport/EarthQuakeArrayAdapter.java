package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class EarthQuakeArrayAdapter extends ArrayAdapter<EarthQuake> {
    public EarthQuakeArrayAdapter(@NonNull Context context, ArrayList<EarthQuake> earthquakes) {
        super(context, 0,earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list, parent, false);
        }
        final EarthQuake currentInfo = getItem(position);

        double mag = currentInfo.getMagnitude();
        DecimalFormat decimalFormatter = new DecimalFormat("0.0");
        String newMag = decimalFormatter.format(mag);
        TextView magnitude = (TextView) listItemView.findViewById(R.id.mag_view);
        magnitude.setText(newMag);

        String place = currentInfo.getPlace();
        String primaryLocation;
        String locationOffset;
        // Check whether the originalLocation string contains the " of " text
        if (place.contains("of")) {
            String[] parts = place.split("of");
            // Location offset should be "5km N " + " of " --> "5km N of"
            locationOffset = parts[0] + "of";
            // Primary location should be "Cairo, Egypt"
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            // The primary location will be the full location string "Pacific-Antarctic Ridge".
            primaryLocation = place;
        }

        TextView location = (TextView) listItemView.findViewById(R.id.location_view);
        location.setText(locationOffset);
        TextView location1 = (TextView) listItemView.findViewById(R.id.location1_view);
        location1.setText(primaryLocation);

        Date dateObject = new Date(currentInfo.getTimeInMilliSeconds());

        TextView date = (TextView) listItemView.findViewById(R.id.date_view);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM DD, yyyy");
        String dateToDisplay = dateFormatter.format(dateObject);
        date.setText(dateToDisplay);

        TextView time = (TextView) listItemView.findViewById(R.id.time_view);
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
        String timeToDisplay = timeFormatter.format(dateObject);
        time.setText(timeToDisplay);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();
        int magnitudeColor = getMagnitudeColor(currentInfo.getMagnitude());
        magnitudeCircle.setColor(magnitudeColor);


        return listItemView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
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
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
