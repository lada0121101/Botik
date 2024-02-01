package org.example;


/**
 *Класс логики бота, обрабатывает сообщение пользователя и возвращает ответ бота;
 * @author Лев Баянов, Шипиловских Лада
 */
public class BotLogics {
    /**
     * сервис для получения инфрмации о погоде в городе
     */
    WeatherService weatherService;

    public BotLogics(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Обрабатывает сообщение пользователя и возвращет ответ в зависимости от того, обычное ли сообщение, или команда старта или помощи
     * @param textMsg сообщение пользователя
     * @param chatId ID чата с пользователем
     * @return текст ответа пользователю
     */
    public String botResponse(String textMsg, String chatId) {
        String start = "Приветствую, напишите название города на английском";
        String help = "Напишите название города на английском и бот пришлëт вам информацию о погоде в этом городе.\nНапример, напишите Moscow, чтобы получить информацию о температуре, кратком описании погоды, скорости ветра в Москве";
        if (textMsg.equals("/start"))
            return start;
        else if (textMsg .equals("/help"))
            return help;
        String cityWeather = weatherService.getWeather(textMsg);
        return cityWeather;
    }
}
