package com.example.a10010582.weatherapp;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeeklyForecast extends Fragment {
    ListView listView;
    ArrayList<DailyForecast> dailyForecasts;
    public WeeklyForecast() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_weekly_forecast, container, false);
        TextView locationTextView = (TextView) v.findViewById(R.id.locationTextView);
        if (HomeFragment.forecastLow.size() > 0) {
            locationTextView.setText(HomeFragment.locationCity + ", " + HomeFragment.locationRegion + ", " + HomeFragment.locationCountry);
        } else {
            locationTextView.setText("");
        }
        listView = (ListView) v.findViewById(R.id.listView);
        dailyForecasts = new ArrayList<>();
        try {
            for (int i = 0; i < HomeFragment.forecastDate.size(); i++) {
                dailyForecasts.add(new DailyForecast(HomeFragment.forecastImages.get(i), HomeFragment.forecastText.get(i), HomeFragment.forecastDate.get(i), HomeFragment.forecastDay.get(i), HomeFragment.forecastHigh.get(i), HomeFragment.forecastLow.get(i)));
            }
        } catch (Exception e){e.printStackTrace();}
        WeeklyForecastAdapter weeklyForecastAdapter =  new WeeklyForecastAdapter(getContext(), R.layout.weekly_forecast_layout, dailyForecasts);
        listView.setAdapter(weeklyForecastAdapter);
        return v;
    }

    public class WeeklyForecastAdapter extends ArrayAdapter<DailyForecast> {
        public WeeklyForecastAdapter(Context context, int resource, List<DailyForecast> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DailyForecast today = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.weekly_forecast_layout, parent, false);
            }

            TextView conditionTextView = (TextView) convertView.findViewById(R.id.conditionTextView);
            TextView dateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
            TextView highTextView = (TextView) convertView.findViewById(R.id.highTextView);
            TextView lowTextView = (TextView) convertView.findViewById(R.id.lowTextView);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
            imageView.setImageResource(today.getImageResource());
            conditionTextView.setText(today.getCondition());
            dateTextView.setText(today.getDay() + ", " + today.getDate());
            highTextView.setText("High: " + today.getHigh());
            lowTextView.setText("Low: " + today.getLow());

            return convertView;

        }
    }

}
