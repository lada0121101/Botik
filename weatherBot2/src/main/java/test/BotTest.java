package test;
import org.example.BotLogics;
import org.example.WeatherService;
import org.glassfish.jersey.jaxb.internal.XmlRootObjectJaxbProvider;
import org.junit.jupiter.api. *;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.*;

public class BotTest {
    @Test
    public void AnswerPatternTest(){
        Properties prop = new Properties();
        String weatherToken;
        try {
            prop.load(XmlRootObjectJaxbProvider.App.class.getClassLoader().getResourceAsStream("config.properties"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        weatherToken = prop.getProperty("WEATHER_SERVICE_TOKEN");
        WeatherService service = new WeatherService(weatherToken);
        BotLogics logic = new BotLogics(service);
        String answerPattern = "City: .*?\nTemperature: -?\\d+\\.\\d+\nDescription: \\w+(\\s\\w+)?\nWindSpeed: \\d+\\.\\d+";
        Assertions.assertTrue(Pattern.matches(answerPattern, logic.botResponse("London", "01")));
    }
    @ParameterizedTest
    @ValueSource(strings = {"Moscow", "Paris", "London", "Rome"})
    public void CityWeatherTest(String city){
        Properties prop = new Properties();
        String weatherToken;
        try {
            prop.load(XmlRootObjectJaxbProvider.App.class.getClassLoader().getResourceAsStream("config.properties"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        weatherToken = prop.getProperty("WEATHER_SERVICE_TOKEN");
        WeatherService service = new WeatherService(weatherToken);
        BotLogics logic = new BotLogics(service);
        String regex = "City: (.*)\n";
        Pattern pattern = Pattern.compile(regex);
        Matcher  matcher = pattern.matcher(logic.botResponse(city, "01"));
        matcher.find();
        Assertions.assertEquals(matcher. group(1), city);
    }

    @Test
    public void SetCommandSetsCityTest(){
        Properties prop = new Properties();
        String weatherToken;
        try {
            prop.load(XmlRootObjectJaxbProvider.App.class.getClassLoader().getResourceAsStream("config.properties"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        weatherToken = prop.getProperty("WEATHER_SERVICE_TOKEN");
        WeatherService service = new WeatherService(weatherToken);
        BotLogics logic = new BotLogics(service);
        String response =logic.botResponse("set Moscow", "01");
        Assertions.assertTrue(logic.isCitySet("01", "Moscow")&&response. equals("Ваш город проживания - Moscow") );
    }

    @Test
    public void CurrentCommandGetCurrentCityWeatherTest(){
        Properties prop = new Properties();
        String weatherToken;
        try {
            prop.load(XmlRootObjectJaxbProvider.App.class.getClassLoader().getResourceAsStream("config.properties"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        weatherToken = prop.getProperty("WEATHER_SERVICE_TOKEN");
        WeatherService service = new WeatherService(weatherToken);
        BotLogics logic = new BotLogics(service);
        logic.botResponse("set Moscow", "01");
        String response =logic.botResponse("current", "01");
        String strPattern = "City: (.+)\n";
        Pattern pattern =Pattern.compile(strPattern);
        Matcher matcher = pattern.matcher(response);
        matcher.lookingAt();
        String city = matcher.group(1);
        Assertions.assertEquals(city, "Moscow" );
    }

    @Test
    public void UnkonownCityMessageTest(){
        Properties prop = new Properties();
        String weatherToken;
        try {
            prop.load(XmlRootObjectJaxbProvider.App.class.getClassLoader().getResourceAsStream("config.properties"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        weatherToken = prop.getProperty("WEATHER_SERVICE_TOKEN");
        WeatherService service = new WeatherService(weatherToken);
        BotLogics logic = new BotLogics(service);
        String response =logic.botResponse("current", "01");
        Assertions.assertEquals(response, "Сначала задайте текущий город");
    }
}
