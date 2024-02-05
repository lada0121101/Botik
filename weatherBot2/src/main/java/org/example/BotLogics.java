package org.example;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *Класс логики бота, обрабатывает сообщение пользователя и возвращает ответ бота;
 * @author Лев Баянов, Шипиловских Лада
 */
public class BotLogics {
    /**
     * сервис для получения инфрмации о погоде в городе
     */
    WeatherService weatherService;
    HashMap<String, String> citys = new HashMap<>();
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
        else if (textMsg.contains("set"))
        {
            String setPattern = "set\\s(.+)";
            Pattern pattern = Pattern.compile(setPattern);
            Matcher matcher =pattern.matcher(textMsg);
            matcher.lookingAt();
            citys.put(chatId, matcher. group(1));
            return "Ваш город проживания - "+ matcher.group(1);
        }
        else if(textMsg.equals("current")){
            if(citys.containsKey(chatId)){
                String currentCity = citys.get(chatId);
                String currentCityWeather = weatherService.getWeather(currentCity);
                return currentCityWeather;
            }
            else return "Сначала задайте текущий город";
        }
        String cityWeather = weatherService.getWeather(textMsg);
        return cityWeather;
    }
}
