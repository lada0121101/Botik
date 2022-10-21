import java.nio.file.FileSystem;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        String TocenBot = "5680682119:AAEoxcQKFRBaZOOhgp_uLxn6z3Eb0aQAu94";
        UserRepository userRepository = new UserRepository();
        WeatherService weatherService = new WeatherService();
        ConsoleBot Bot = new ConsoleBot(userRepository, weatherService);


    }
}