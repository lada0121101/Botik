package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class WeatherService {

    GiveBotToken giveBotToken;

    WeatherService(GiveBotToken giveBotToken) {
        this.giveBotToken = giveBotToken;

    }

    public String getWeather(String city) {
        String weatherToken = giveBotToken.weatherToken;
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + weatherToken + "&units=metric";

        URL obj = null;
        try {
            obj = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection) obj.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }

        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String inputLine;
        StringBuffer response = new StringBuffer();

        while (true) {
            try {
                if (!((inputLine = in.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            response.append(inputLine);
        }
        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        response.toString();

        int temp = response.indexOf("temp") + 5;
        return "temp: " + response.substring(temp, temp + 5) + " degrees";


    }
}