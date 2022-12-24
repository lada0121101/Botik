package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        GiveBotToken giveBotToken = new GiveBotToken();
        WeatherService weatherService = new WeatherService(giveBotToken);
        UserRepository userRepository = new UserRepository();
        BotLogics botLogics = new BotLogics(userRepository,weatherService);
        WeatherTelegramBot weatherTelegramBot = new WeatherTelegramBot(giveBotToken, botLogics);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(weatherTelegramBot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}