package org.example;

import org.glassfish.jersey.jaxb.internal.XmlRootObjectJaxbProvider;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties prop = new Properties();
        String BOT_TOKEN = "";
        String BOT_NAME = "";
        String WEATHER_SERVICE_TOKEN = "";
        try {
            prop.load(XmlRootObjectJaxbProvider.App.class.getClassLoader().getResourceAsStream("config.properties"));
            BOT_TOKEN = prop.getProperty("BOT_TOKEN");
            BOT_NAME = prop.getProperty("BOT_NAME");
            WEATHER_SERVICE_TOKEN = prop.getProperty("WEATHER_SERVICE_TOKEN");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        WeatherService weatherService = new WeatherService(WEATHER_SERVICE_TOKEN);
        BotLogics botLogics = new BotLogics(weatherService);
        WeatherTelegramBot weatherTelegramBot = new WeatherTelegramBot(botLogics, BOT_TOKEN, BOT_NAME);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(weatherTelegramBot);
        }catch (TelegramApiException e) {
           throw new RuntimeException(e);
        }
    }
}