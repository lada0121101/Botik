package org.example;

import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Класс предоставления информации о погоде в городе
 * @author Баянов Лев, Шипиловских Лада
 */
public class WeatherService {
    /**
     * токен для WeatherAPI
     */
    String weatherToken;
    WeatherService(String weatherToken){
        this.weatherToken = weatherToken;
    }

    /**
     * возвращает информацию о погоде в заданном городе
     * @param city название города
     * @return информаця о погоде в городе
     */
    public String getWeather(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + weatherToken + "&units=metric";

        StringBuffer response = new StringBuffer();
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while (true) {
                try {
                    if (!((inputLine = in.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                response.append(inputLine);
            }
            in.close();

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            return "Перепроверьте название города";
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(response);
        return parserJson(response.toString());
    }

    /**
     * формирует из json ответа сервера понятное сообщение о погоде
     * @param textMsg json ответ на запрос серверу
     * @return сообщение в формате City:[название города] Temperature:[температура в городе] Description:[краткое описание погоды] WindSpeed:[скорость ветра]
     */
    private String parserJson(String textMsg) {
        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(textMsg, Map.class);

        String cityName = "City: " + map.get("name");

        Map<String, Object> main = (Map<String, Object>) map.get("main");
        String temp = "Temperature: " + main.get("temp");

        List<Map<String, Object>> weather = (List<Map<String, Object>>) map.get("weather");
        String Description = "Description: " + weather.get(0).get("description");

        Map<String, Object> wind = (Map<String, Object>) map.get("wind");
        String windSpeed = "WindSpeed: " + wind.get("speed");
                String answer = (cityName+"\n"+
                         temp+"\n"+
                         Description+"\n"+
                         windSpeed+"\n");

        return answer;
    }
}