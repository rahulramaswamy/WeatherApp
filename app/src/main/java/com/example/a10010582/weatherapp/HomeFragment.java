package com.example.a10010582.weatherapp;


import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
//ToDo overwrite backup when api works or switch to OpenWeatherMap API
public class HomeFragment extends Fragment {
    static final String DEGREE_SIGN = "\u00b0";
    boolean firstLaunch = true;
    Button button, backupButton;

    String url, info;
    URL yahooURL;
    URLConnection urlConnection;
    InputStream inputStream;
    BufferedReader bufferedReader;
    JSONObject weatherInfo;
    EditText editText;
    static ProgressBar progressBar;
    static String unitsDistance, unitsPressure, unitsSpeed, unitsTemperature,
            locationCity, locationCountry, locationRegion,
            feelsLike, windDirection, windSpeed,
            atmosphereHumidity, atmospherePressure, atmosphereVisibility,
            astronomySunrise, astronomySunset, todayConditionCode,
            todayConditionDate, todayConditionTemp, todayConditionText, quote;
    static ArrayList<String>  forecastDate, forecastDay, forecastHigh, forecastLow, forecastText;
    static ArrayList<Integer> forecastCode, forecastImages;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        if (firstLaunch) {
            forecastDate = new ArrayList<>();
            forecastDay = new ArrayList<>();
            forecastHigh = new ArrayList<>();
            forecastLow = new ArrayList<>();
            forecastText = new ArrayList<>();
            forecastCode = new ArrayList<>();
            forecastImages = new ArrayList<>();
            todayConditionCode = "-1";
            firstLaunch = false;

        }
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        progressBar.setProgress(0);
        editText = (EditText) v.findViewById(R.id.editText);
        button = (Button) v.findViewById(R.id.button);
        backupButton = (Button) v.findViewById(R.id.backupButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forecastDate = new ArrayList<>();
                forecastDay = new ArrayList<>();
                forecastHigh = new ArrayList<>();
                forecastLow = new ArrayList<>();
                forecastText = new ArrayList<>();
                forecastCode = new ArrayList<>();
                forecastImages = new ArrayList<>();
                WeatherThread downloadWeather = new WeatherThread();
                if (!(editText.getText().toString().equals("")))
                    downloadWeather.execute(editText.getText().toString());
            }
        });
        backupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    forecastDate = new ArrayList<>();
                    forecastDay = new ArrayList<>();
                    forecastHigh = new ArrayList<>();
                    forecastLow = new ArrayList<>();
                    forecastText = new ArrayList<>();
                    forecastCode = new ArrayList<>();
                    forecastImages = new ArrayList<>();
                    /*AssetManager aM = getContext().getAssets();
                    InputStream iS = aM.open("backupJson.txt");
                    BufferedReader bf = new BufferedReader(new InputStreamReader(iS));
                    String s = bf.readLine();
                    System.out.println(s);
                    JSONObject weatherInfo = new JSONObject(s);
                    bf.close();*/
                    String s = "{\"query\":{\"count\":1,\"created\":\"2016-12-14T13:15:56Z\",\"lang\":\"en-US\",\"results\":{\"channel\":{\"units\":{\"distance\":\"mi\",\"pressure\":\"in\",\"speed\":\"mph\",\"temperature\":\"F\"},\"title\":\"Yahoo! Weather - South Brunswick, NJ, US\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-12761289/\",\"description\":\"Yahoo! Weather for South Brunswick, NJ, US\",\"language\":\"en-us\",\"lastBuildDate\":\"Wed, 14 Dec 2016 08:15 AM EST\",\"ttl\":\"60\",\"location\":{\"city\":\"South Brunswick\",\"country\":\"United States\",\"region\":\" NJ\"},\"wind\":{\"chill\":\"25\",\"direction\":\"300\",\"speed\":\"11\"},\"atmosphere\":{\"humidity\":\"83\",\"pressure\":\"1011.0\",\"rising\":\"0\",\"visibility\":\"16.1\"},\"astronomy\":{\"sunrise\":\"7:14 am\",\"sunset\":\"4:33 pm\"},\"image\":{\"title\":\"Yahoo! Weather\",\"width\":\"142\",\"height\":\"18\",\"link\":\"http://weather.yahoo.com\",\"url\":\"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\":{\"title\":\"Conditions for South Brunswick, NJ, US at 07:00 AM EST\",\"lat\":\"40.389568\",\"long\":\"-74.539688\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-12761289/\",\"pubDate\":\"Wed, 14 Dec 2016 07:00 AM EST\",\"condition\":{\"code\":\"31\",\"date\":\"Wed, 14 Dec 2016 07:00 AM EST\",\"temp\":\"31\",\"text\":\"Clear\"},\"forecast\":[{\"code\":\"30\",\"date\":\"14 Dec 2016\",\"day\":\"Wed\",\"high\":\"40\",\"low\":\"30\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"15 Dec 2016\",\"day\":\"Thu\",\"high\":\"29\",\"low\":\"20\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"16 Dec 2016\",\"day\":\"Fri\",\"high\":\"28\",\"low\":\"18\",\"text\":\"Partly Cloudy\"},{\"code\":\"5\",\"date\":\"17 Dec 2016\",\"day\":\"Sat\",\"high\":\"47\",\"low\":\"24\",\"text\":\"Rain And Snow\"},{\"code\":\"39\",\"date\":\"18 Dec 2016\",\"day\":\"Sun\",\"high\":\"56\",\"low\":\"32\",\"text\":\"Scattered Showers\"},{\"code\":\"28\",\"date\":\"19 Dec 2016\",\"day\":\"Mon\",\"high\":\"31\",\"low\":\"23\",\"text\":\"Mostly Cloudy\"},{\"code\":\"28\",\"date\":\"20 Dec 2016\",\"day\":\"Tue\",\"high\":\"31\",\"low\":\"20\",\"text\":\"Mostly Cloudy\"},{\"code\":\"28\",\"date\":\"21 Dec 2016\",\"day\":\"Wed\",\"high\":\"40\",\"low\":\"21\",\"text\":\"Mostly Cloudy\"},{\"code\":\"28\",\"date\":\"22 Dec 2016\",\"day\":\"Thu\",\"high\":\"45\",\"low\":\"31\",\"text\":\"Mostly Cloudy\"},{\"code\":\"12\",\"date\":\"23 Dec 2016\",\"day\":\"Fri\",\"high\":\"43\",\"low\":\"35\",\"text\":\"Rain\"}],\"description\":\"<![CDATA[<img src=\\\"http://l.yimg.com/a/i/us/we/52/31.gif\\\"/>\\n<BR />\\n<b>Current Conditions:</b>\\n<BR />Clear\\n<BR />\\n<BR />\\n<b>Forecast:</b>\\n<BR /> Wed - Partly Cloudy. High: 40Low: 30\\n<BR /> Thu - Partly Cloudy. High: 29Low: 20\\n<BR /> Fri - Partly Cloudy. High: 28Low: 18\\n<BR /> Sat - Rain And Snow. High: 47Low: 24\\n<BR /> Sun - Scattered Showers. High: 56Low: 32\\n<BR />\\n<BR />\\n<a href=\\\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-12761289/\\\">Full Forecast at Yahoo! Weather</a>\\n<BR />\\n<BR />\\n(provided by <a href=\\\"http://www.weather.com\\\" >The Weather Channel</a>)\\n<BR />\\n]]>\",\"guid\":{\"isPermaLink\":\"false\"}}}}}}";
                    JSONObject wI = new JSONObject(s);
                    parseInfo(wI);
                    initImages();
                    initQuotes();
                    System.out.println(weatherInfo.toString());
                    TabHost tabHost = (TabHost) getActivity().findViewById(R.id.tabHost);
                    tabHost.setCurrentTab(1);
                    backupButton.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), "Loaded Data From Last Backup: " + todayConditionDate, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return v;
    }
    public void parseInfo(JSONObject jsonObject) {
        try {
            JSONObject channel = jsonObject.getJSONObject("query").getJSONObject("results").getJSONObject("channel");
            JSONArray forecast = channel.getJSONObject("item").getJSONArray("forecast");
            unitsDistance = channel.getJSONObject("units").getString("distance");
            unitsPressure = channel.getJSONObject("units").getString("pressure");
            unitsSpeed = channel.getJSONObject("units").getString("speed");
            unitsTemperature = channel.getJSONObject("units").getString("temperature");
            locationCity = channel.getJSONObject("location").getString("city");
            locationCountry = channel.getJSONObject("location").getString("country");
            locationRegion = channel.getJSONObject("location").getString("region");
            feelsLike = channel.getJSONObject("wind").getString("chill");
            windDirection = channel.getJSONObject("wind").getString("direction");
            windSpeed = channel.getJSONObject("wind").getString("speed");
            atmosphereHumidity = channel.getJSONObject("atmosphere").getString("humidity");
            atmospherePressure = channel.getJSONObject("atmosphere").getString("pressure");
            atmosphereVisibility = channel.getJSONObject("atmosphere").getString("visibility");
            astronomySunrise = channel.getJSONObject("astronomy").getString("sunrise");
            astronomySunset = channel.getJSONObject("astronomy").getString("sunset");
            todayConditionCode = channel.getJSONObject("item").getJSONObject("condition").getString("code");
            todayConditionDate = channel.getJSONObject("item").getJSONObject("condition").getString("date");
            todayConditionTemp = channel.getJSONObject("item").getJSONObject("condition").getString("temp");
            todayConditionText = channel.getJSONObject("item").getJSONObject("condition").getString("text");


            for (int i = 0; i <= forecast.length(); i++) {
                forecastDate.add(forecast.getJSONObject(i).getString("date"));
                forecastDay.add(forecast.getJSONObject(i).getString("day"));
                forecastHigh.add(forecast.getJSONObject(i).getString("high"));
                forecastLow.add(forecast.getJSONObject(i).getString("low"));
                forecastText.add(forecast.getJSONObject(i).getString("text"));
                forecastCode.add(forecast.getJSONObject(i).getInt("code"));
                System.out.println("Worked");
            }
        } catch (Exception e) {e.printStackTrace();}
    }
    public class WeatherThread extends AsyncTask<String, Integer, Void> {
        int count = 0;

        @Override
        protected Void doInBackground(String... params) {
            try {
                String location = "";

                for (int i = 0; i < params.length; i++) {
                    location += params[i];
                    if (i != params.length - 1)
                        location += " ";
                }
                publishProgress(count++);

                url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" +
                        URLEncoder.encode(location, "UTF-8") +
                        "%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
                publishProgress(count++);
                yahooURL = new URL(url);
                publishProgress(count++);
                urlConnection = yahooURL.openConnection();
                publishProgress(count++);
                inputStream = urlConnection.getInputStream();
                publishProgress(count++);
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                publishProgress(count++);
                info = bufferedReader.readLine();
                publishProgress(count++);
                weatherInfo = new JSONObject(info);
                publishProgress(count++);
                bufferedReader.close();
                publishProgress(count++);
                Log.d("URL", weatherInfo.toString());
                publishProgress(count++);
                parseInfo(weatherInfo);
                publishProgress(count++);


            } catch (Exception e) {}
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0] * 10);
            Log.d("Values", values[0].toString());
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setProgress(0);
            initImages();
            initQuotes();
            try {
                if (!weatherInfo.getJSONObject("query").get("results").equals(null)) {

                    TabHost tabHost = (TabHost) getActivity().findViewById(R.id.tabHost);
                    tabHost.setCurrentTab(1);
                    backupButton.setVisibility(View.INVISIBLE);
                } else {
                    Toast.makeText(getContext(), "Error Retrieving Data. Press Backup Button to Load Last Backed Up File", Toast.LENGTH_LONG).show();
                    backupButton.setVisibility(View.VISIBLE);
                }
            } catch (JSONException e) {}
            super.onPostExecute(aVoid);

        }
    }
    public void initImages() {
        for (int i = 0; i < forecastCode.size(); i++){
            switch (forecastCode.get(i)){
                case 0: forecastImages.add(R.drawable.zero);
                    break;
                case 1: forecastImages.add(R.drawable.one);
                    break;
                case 2: forecastImages.add(R.drawable.two);
                    break;
                case 3: forecastImages.add(R.drawable.three);
                    break;
                case 4: forecastImages.add(R.drawable.four);
                    break;
                case 5: forecastImages.add(R.drawable.five);
                    break;
                case 6: forecastImages.add(R.drawable.six);
                    break;
                case 7: forecastImages.add(R.drawable.seven);
                    break;
                case 8: forecastImages.add(R.drawable.eight);
                    break;
                case 9: forecastImages.add(R.drawable.nine);
                    break;
                case 10:forecastImages.add(R.drawable.ten);
                    break;
                case 11:forecastImages.add(R.drawable.eleven);
                    break;
                case 12:forecastImages.add(R.drawable.twelve);
                    break;
                case 13:forecastImages.add(R.drawable.thirteen);
                    break;
                case 14:forecastImages.add(R.drawable.fourteen);
                    break;
                case 15:forecastImages.add(R.drawable.fifteen);
                    break;
                case 16:forecastImages.add(R.drawable.sixteen);
                    break;
                case 17:forecastImages.add(R.drawable.seventeen);
                    break;
                case 18:forecastImages.add(R.drawable.eighteen);
                    break;
                case 19:forecastImages.add(R.drawable.nineteen);
                    break;
                case 20:forecastImages.add(R.drawable.twenty);
                    break;
                case 21:forecastImages.add(R.drawable.twentyone);
                    break;
                case 22:forecastImages.add(R.drawable.twentytwo);
                    break;
                case 23:forecastImages.add(R.drawable.twentythree);
                    break;
                case 24:forecastImages.add(R.drawable.twentyfour);
                    break;
                case 25:forecastImages.add(R.drawable.twentyfive);
                    break;
                case 26:forecastImages.add(R.drawable.twentysix);
                    break;
                case 27:forecastImages.add(R.drawable.twentyseven);
                    break;
                case 28:forecastImages.add(R.drawable.twentyeight);
                    break;
                case 29:forecastImages.add(R.drawable.twentynine);
                    break;
                case 30:forecastImages.add(R.drawable.thirty);
                    break;
                case 31:forecastImages.add(R.drawable.thirtyone);
                    break;
                case 32:forecastImages.add(R.drawable.thirtytwo);
                    break;
                case 33:forecastImages.add(R.drawable.thirtythree);
                    break;
                case 34:forecastImages.add(R.drawable.thirtyfour);
                    break;
                case 35:forecastImages.add(R.drawable.thirtyfive);
                    break;
                case 36:forecastImages.add(R.drawable.thirtysix);
                    break;
                case 37:forecastImages.add(R.drawable.thirtyseven);
                    break;
                case 38:forecastImages.add(R.drawable.thirtyeight);
                    break;
                case 39:forecastImages.add(R.drawable.thirtynine);
                    break;
                case 40:forecastImages.add(R.drawable.forty);
                    break;
                case 41:forecastImages.add(R.drawable.fortyone);
                    break;
                case 42:forecastImages.add(R.drawable.fortytwo);
                    break;
                case 43:forecastImages.add(R.drawable.fortythree);
                    break;
                case 44:forecastImages.add(R.drawable.fortyfour);
                    break;
                case 45:forecastImages.add(R.drawable.fortyfive);
                    break;
                case 46:forecastImages.add(R.drawable.fortysix);
                    break;
                case 47:forecastImages.add(R.drawable.fortyseven);
                    break;
                default:forecastImages.add(R.drawable.na);
                    break;
            }
        }

    }
    public void initQuotes(){
        Log.d("TTT",todayConditionCode);
        switch (Integer.parseInt(todayConditionCode)){
            case 0:
            case 1:
            case 2:
                    quote = "Pidgeot is making trouble by using Hurricane!";
                break;
            case 37:
            case 38:
            case 39:
                  quote = "It looks like Zapdos just used Thunderstorm!";
                break;
            case 7:
            case 13:
            case 14:
            case 15:
            case 16:
            case 35:
                    quote = "Its Snowing and Lapras is nearby using Blizzard!";
                break;
            case 5:
            case 6:
            case 8:
            case 9:
            case 11:
            case 12:
            case 10:
            case 40:
                    quote = "Squirtle is making it rain with Rain Dance!";
                break;
            case 19:
            case 20:
            case 21:
            case 22:
                    quote = "SandSlash is making a mess using Sandstorm!";
                break;
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
                    quote = "It looks like there is an Altaria approaching!";
                break;
            case 31:
            case 32:
            case 33:
            case 34:
                    quote = "Charmander just cleared the skies with Sunny Day!";
                break;
            case 25:
                    quote = "Brrrrr.... Articuno used Sheer Cold!";
                break;
            case 36:
                    quote = "Ahhhhh.... Moltres used Heat Blast!";
                break;
            case 23:
            case 24:
                    quote = "Fearow is blowing everything away with Gust!";
                break;
            default:
                    quote = "Uh Oh!";
                break;
        }
    }
}