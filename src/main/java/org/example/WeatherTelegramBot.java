package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalTime;

/**
 * Класс Telgram бота, описывает реакцию на обновление, хранит имя бота, токен, связывает логику бота с самим ботом;
 * @author Лев Баянов
 */
public class WeatherTelegramBot extends TelegramLongPollingBot implements Runnable{
    /**
     *токен бота(уникальная строка, нужная для управления ботом)
     */
    String BOT_TOKEN;
    /**
     * название бота
     */
    String BOT_NAME;
    /**
     * логика бота, описывающая способ обработки собщения пользоваетелей
     */
    BotLogics botLogics;
    WeatherTelegramBot(BotLogics botLogics, String BOT_TOKEN, String BOT_NAME)
    {
        this.botLogics = botLogics;
        this.BOT_TOKEN = BOT_TOKEN;
        this.BOT_NAME = BOT_NAME;
    }

    /**
     *
     * @return Имя бота
     */
    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    /**
     *
     * @return токен бота
     */
    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    /**
     * ипользуя логику бота отвечает пользователю
     * @param update информация об обновлени состояния одной из бесед бота
     */
    @Override
    public void onUpdateReceived(Update update) {
        Message inMess = update.getMessage();
        String chatId = inMess.getChatId().toString();
        String userMessage = inMess.getText();

        String answer = botLogics.botResponse(userMessage, chatId);

        SendMessage outMess = new SendMessage();
        outMess.setChatId(chatId);
        outMess.setText(answer);

        try {
            execute(outMess);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run(){
        LocalTime currentTime =LocalTime.now();
        for (var chatId : botLogics.getChatIdsByTime(currentTime)){
            String userCurrentCityWeather= botLogics.botResponse("current", chatId);
            SendMessage answer=new SendMessage();
            answer.setChatId(chatId);
            answer.setText(userCurrentCityWeather);
            try{
                execute(answer);
            }
            catch (TelegramApiException e){
                throw new RuntimeException(e);
            }
        }
    }
}
