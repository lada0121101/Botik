package org.example;

public class BotLogics {

    UserRepository userRepository;
    WeatherService weatherService;

    BotLogics(UserRepository userRepository, WeatherService weatherService)
    {
        this.userRepository = userRepository;
        this.weatherService = weatherService;
    }

    public String parseMessage(String textMsg)
    {
        String response;
        if(textMsg.equals("/start"))
            response = "Приветствую, бот знает погоду в любом городе \nПросто напиши название города на англиском";
        else
            response =  weatherService.getWeather(textMsg,"b0a7d4ef65455317ec52fe386eb9140d");

        return response;
    }

}
