import java.util.HashMap;
import java.util.Map;

public class WeatherService {
    Map<String,String> map = new HashMap<String, String>();
    WeatherService()
    {
        map.put("Екатеринбург", "Сеичас 21.30, облачно, +1, Вероятность осадков: 19%, Влажность: 84%, Ветер: 3 м/с");
        map.put("Астана", "Ясно +10");
        map.put("Киев", "Дождь +6");
        map.put("Дубай", "Ясно +26");

    }

    public String GetWeather(String WeatherRequest) {
       return map.get(WeatherRequest);
    }
}