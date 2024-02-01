package org.example;


import org.telegram.telegrambots.meta.api.methods.groupadministration.DeleteChatStickerSet;

public class BotLogics {
    WeatherService weatherService;

    public BotLogics(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public String botResponse(String textMsg, String chatId) {
        String start = "Приветствую, напишите название города на английском";
        String help = "Напишите название города на английском и бот пришлëт вам инфаааацию о погоде в этом городе.\nНапример, напишите Moscow, чтобы получить информацию о температуре, кратком описании погоды, скорости ветра в Москве";
        if (textMsg.equals("/start"))
            return start;
        else if (textMsg .equals("/help"))
            return help;
        String cityWeather = weatherService.getWeather(textMsg);
        return cityWeather;
    }
}



