import java.nio.file.FileSystem;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        
        UserRepository userRepository = new UserRepository();
        WeatherService weatherService = new WeatherService();
        ConsoleBot Bot = new ConsoleBot(userRepository, weatherService);


    }
}
