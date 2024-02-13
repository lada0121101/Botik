package org.example;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
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
    /**
     * хранилище городов ползователей.Ключ -Id, значение - текущий город
     */
    HashMap<String, String> citys = new HashMap<>();
    /**
     * Хранилище времени рассылки. Первый индекс -час , второй-минута, множество- множество chatId, подписанных на получившееся время
     */
    HashSet<String>[][] mailingStorage= new HashSet[24][60];

    public BotLogics(WeatherService weatherService) {
        this.weatherService = weatherService;
        for (int i =0;i<24;i++){
            for(int j=0;j<60;j++){
                mailingStorage[i][j]=new HashSet<String>();
            }
        }
    }

    /**
     * Обрабатывает сообщение пользователя и возвращет ответ в зависимости от того, обычное ли сообщение, или команда старта или помощи
     * @param textMsg сообщение пользователя
     * @param chatId ID чата с пользователем
     * @return текст ответа пользователю
     */
    public String botResponse(String textMsg, String chatId) {
        String start = "Приветствую, напишите название города на английском";
        String help = "Напишите название города на английском и бот пришлëт вам информацию о погоде в этом городе.\nНапример, напишите Moscow, чтобы получить информацию о температуре, кратком описании погоды, скорости ветра в Москве.Вы так же можете задать текущий город командой set [название города] и получить информацию о температуре в нем написав current";
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
        else if(textMsg.startsWith("/schedule")){
            if(citys.containsKey(chatId)) {
                String schedulePattern = "/schedule\\s(\\d{2}):(\\d{2})";
                Pattern pattern = Pattern.compile(schedulePattern);
                Matcher matcher = pattern.matcher(textMsg);
                matcher.find();
                String hours = matcher.group(1);
                String minuts = matcher.group(2);
                mailingStorage[Integer.parseInt(hours)][Integer.parseInt(minuts)].add(chatId);
                String answer = "We will send you information about city every day at " + hours + ":" + minuts + ". To stop mailing write /unschedule to the bot.";
                return answer;
            }
            else return "Сначала задайте текущий город";
        }
        else if (textMsg. startsWith("/unschedule")) {
            for (int i = 0; i < 24; i++){
                for (int j = 0;j <60;j++){
                    if(mailingStorage[i][j].contains(chatId)){
                        mailingStorage[i][j].remove(chatId);
                    }
                }
            }
            return "The mailing is stoped";
        }
        String cityWeather = weatherService.getWeather(textMsg);
        return cityWeather;
    }

    /**
     * @param chatID-id города
     * @param city-название города
     * @return есть ли запись с id и city в словаре citys
     */
     public boolean isCitySet(String chatID, String city){
        return citys.containsKey(chatID) && citys.get(chatID).equals(city);
     }

     public HashSet<String> getChatIdsByTime(LocalTime time){
         return mailingStorage[time.getHour()][time.getMinute()];
     }

    /**
     *
     * @param chatID
     * @param hour
     * @param minut
     * @return сохранëн ли chatID на данное время рассылки.
     */

     public boolean IsTimeSet(String chatID, int hour, int minut){
         return mailingStorage[hour][minut].contains(chatID);
     }

}
