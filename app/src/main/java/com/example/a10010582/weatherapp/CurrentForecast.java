package com.example.a10010582.weatherapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentForecast extends Fragment {
    TextView locationTextView;
    ImageView imageView;
    Integer imgRes;
    TextView currentTextView, dateTextView, highTextView, lowTextView, feelsLikeTextView,
            windDirectionTextView, windSpeedTextView,
            humidityTextView, pressureTextView, visibilityTextView,
            sunriseTextView, sunsetTextView, conditionTextView, quoteTextView;
    View view;
    public CurrentForecast() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_current_forecast, container, false);
        locationTextView = (TextView) v.findViewById(R.id.locationTextView2);
        view = v.findViewById(R.id.view2);
        currentTextView = (TextView) v.findViewById(R.id.currentTextView);
        dateTextView = (TextView) v.findViewById(R.id.dateTextView);
        highTextView = (TextView) v.findViewById(R.id.highTextView2);
        lowTextView = (TextView) v.findViewById(R.id.lowTextView2);
        feelsLikeTextView = (TextView) v.findViewById(R.id.feelsLikeTextView);
        windDirectionTextView = (TextView) v.findViewById(R.id.windDirectionTextView);
        windSpeedTextView = (TextView) v.findViewById(R.id.windSpeedTextView);
        humidityTextView = (TextView) v.findViewById(R.id.humidityTextView);
        pressureTextView = (TextView) v.findViewById(R.id.pressureTextView);
        visibilityTextView = (TextView) v.findViewById(R.id.visibilityTextView);
        sunriseTextView = (TextView) v.findViewById(R.id.sunriseTextView);
        sunsetTextView = (TextView) v.findViewById(R.id.sunsetTextView);
        conditionTextView = (TextView) v.findViewById(R.id.conditionTextView);
        quoteTextView = (TextView) v.findViewById(R.id.quoteTextView);
        imageView = (ImageView) v.findViewById(R.id.imageView2);
        try {
            getImage();
            if (HomeFragment.forecastLow.size() > 0) {
                locationTextView.setText(HomeFragment.locationCity + ", " + HomeFragment.locationRegion + ", " + HomeFragment.locationCountry);
            } else {
                locationTextView.setText("");
            }
            imageView.setImageResource(imgRes);
            currentTextView.setText(HomeFragment.todayConditionTemp + HomeFragment.DEGREE_SIGN + HomeFragment.unitsTemperature);
            highTextView.setText("High: " + HomeFragment.forecastHigh.get(0) + HomeFragment.DEGREE_SIGN + HomeFragment.unitsTemperature);
            lowTextView.setText("Low: " + HomeFragment.forecastLow.get(0) + HomeFragment.DEGREE_SIGN + HomeFragment.unitsTemperature);
            feelsLikeTextView.setText("Feels Like: " + HomeFragment.feelsLike + HomeFragment.DEGREE_SIGN + HomeFragment.unitsTemperature);
            dateTextView.setText(HomeFragment.todayConditionDate);
            windDirectionTextView.setText(HomeFragment.windDirection + HomeFragment.DEGREE_SIGN);
            windSpeedTextView.setText(HomeFragment.windSpeed + HomeFragment.unitsSpeed);
            humidityTextView.setText(HomeFragment.atmosphereHumidity + "%");
            pressureTextView.setText(HomeFragment.atmospherePressure + HomeFragment.unitsPressure);
            visibilityTextView.setText(HomeFragment.atmosphereVisibility + HomeFragment.unitsDistance);
            quoteTextView.setText(HomeFragment.quote);
            sunriseTextView.setText(HomeFragment.astronomySunrise);
            sunsetTextView.setText(HomeFragment.astronomySunset);
            conditionTextView.setText(HomeFragment.todayConditionText);
            view.setVisibility(View.INVISIBLE);
        } catch (Exception e){
            e.printStackTrace();
        }

        return v;
    }
    public void getImage() {
        switch (Integer.parseInt(HomeFragment.todayConditionCode)) {
            case 0:
                imgRes = (R.drawable.zero);
                break;
            case 1:
                imgRes = (R.drawable.one);
                break;
            case 2:
                imgRes = (R.drawable.two);
                break;
            case 3:
                imgRes = (R.drawable.three);
                break;
            case 4:
                imgRes = (R.drawable.four);
                break;
            case 5:
                imgRes = (R.drawable.five);
                break;
            case 6:
                imgRes = (R.drawable.six);
                break;
            case 7:
                imgRes = (R.drawable.seven);
                break;
            case 8:
                imgRes = (R.drawable.eight);
                break;
            case 9:
                imgRes = (R.drawable.nine);
                break;
            case 10:
                imgRes = (R.drawable.ten);
                break;
            case 11:
                imgRes = (R.drawable.eleven);
                break;
            case 12:
                imgRes = (R.drawable.twelve);
                break;
            case 13:
                imgRes = (R.drawable.thirteen);
                break;
            case 14:
                imgRes = (R.drawable.fourteen);
                break;
            case 15:
                imgRes = (R.drawable.fifteen);
                break;
            case 16:
                imgRes = (R.drawable.sixteen);
                break;
            case 17:
                imgRes = (R.drawable.seventeen);
                break;
            case 18:
                imgRes = (R.drawable.eighteen);
                break;
            case 19:
                imgRes = (R.drawable.nineteen);
                break;
            case 20:
                imgRes = (R.drawable.twenty);
                break;
            case 21:
                imgRes = (R.drawable.twentyone);
                break;
            case 22:
                imgRes = (R.drawable.twentytwo);
                break;
            case 23:
                imgRes = (R.drawable.twentythree);
                break;
            case 24:
                imgRes = (R.drawable.twentyfour);
                break;
            case 25:
                imgRes = (R.drawable.twentyfive);
                break;
            case 26:
                imgRes = (R.drawable.twentysix);
                break;
            case 27:
                imgRes = (R.drawable.twentyseven);
                break;
            case 28:
                imgRes = (R.drawable.twentyeight);
                break;
            case 29:
                imgRes = (R.drawable.twentynine);
                break;
            case 30:
                imgRes = (R.drawable.thirty);
                break;
            case 31:
                imgRes = (R.drawable.thirtyone);
                break;
            case 32:
                imgRes = (R.drawable.thirtytwo);
                break;
            case 33:
                imgRes = (R.drawable.thirtythree);
                break;
            case 34:
                imgRes = (R.drawable.thirtyfour);
                break;
            case 35:
                imgRes = (R.drawable.thirtyfive);
                break;
            case 36:
                imgRes = (R.drawable.thirtysix);
                break;
            case 37:
                imgRes = (R.drawable.thirtyseven);
                break;
            case 38:
                imgRes = (R.drawable.thirtyeight);
                break;
            case 39:
                imgRes = (R.drawable.thirtynine);
                break;
            case 40:
                imgRes = (R.drawable.forty);
                break;
            case 41:
                imgRes = (R.drawable.fortyone);
                break;
            case 42:
                imgRes = (R.drawable.fortytwo);
                break;
            case 43:
                imgRes = (R.drawable.fortythree);
                break;
            case 44:
                imgRes = (R.drawable.fortyfour);
                break;
            case 45:
                imgRes = (R.drawable.fortyfive);
                break;
            case 46:
                imgRes = (R.drawable.fortysix);
                break;
            case 47:
                imgRes = (R.drawable.fortyseven);
                break;
            default:
                imgRes = (R.drawable.na);
                break;
        }
    }

}
