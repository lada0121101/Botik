package org.example;


public class BotLogics {
    WeatherService weatherService;
    public BotLogics( WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public String botResponse(String textMsg, String chatId)
    {
        String cityWeather = weatherService.getWeather(textMsg);
        return cityWeather;
    }
}



