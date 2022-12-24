package org.example;

public class BotLogics {

    UserRepository userRepository;
    WeatherService weatherService;

    public BotLogics(UserRepository userRepository, WeatherService weatherService) {
        this.weatherService = weatherService;
        this.userRepository = userRepository;
    }
    public String botResponse(String textMsg, String chatId)
    {
        String response;
        userRepository.updateUserStatus(textMsg,chatId);
        String cityName = userRepository.cityName;
        response = weatherService.getWeather(cityName);
        return response;
    }

    private String parseMessage(String textMsg)
    {
        return "";
    }

}
