package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class WeatherTelegramBot extends TelegramLongPollingBot {

    String BOT_TOKEN;
    String BOT_NAME;

    BotLogics botLogics;

    WeatherTelegramBot(GiveBotToken giveBotToken, BotLogics botLogics)
    {
        this.botLogics = botLogics;
        BOT_TOKEN = giveBotToken.botToken;
        BOT_NAME = giveBotToken.botToken;

    }

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


        String answer;
        answer = botLogics.botResponse(userMessage, chatId);

        SendMessage outMess = new SendMessage();
        outMess.setChatId(chatId);
        outMess.setText(answer);

        try {
            execute(outMess);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
