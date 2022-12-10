package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class WeatherTelegramBot extends TelegramLongPollingBot {

    givBotToken givBotToken = new givBotToken();
    String BOT_TOKEN = givBotToken.BOT_TOKEN;
    String BOT_NAME = givBotToken.BOT_NAME;


    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message inMess = update.getMessage();

        String chatId = inMess.getChatId().toString();
        String userMessage = inMess.getText();
        SendMessage outMess = new SendMessage();

        UserRepository userRepository = new UserRepository(chatId, userMessage);
        WeatherService weatherService = new WeatherService();
        BotLogics botLogics = new BotLogics(userRepository, weatherService);

        String response = botLogics.parseMessage(userMessage);
        outMess.setChatId(chatId);
        outMess.setText(response);

        try {
            execute(outMess);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
