import java.util.Scanner;

public class ConsoleBot {
    UserRepository userRepository;
    WeatherService weatherService;

    ConsoleBot(UserRepository userRepository,WeatherService weatherService)
    {
        this.userRepository = userRepository;
        this.weatherService = weatherService;
        StartBot();
    }

    public void StartBot()
    {
        while (true)
        {
           String userMessage = ReadConsole();
           String BotResponse = BotLogic(userMessage);
           WriteToConsole(BotResponse);
        }
    }

    public String ReadConsole()
    {
        Scanner scan = new Scanner(System.in);
        String message = scan.nextLine();
        return message;
    }

    public void WriteToConsole(String BotResponse)
    {
        System.out.println(BotResponse);
    }

    public String BotLogic(String userMessage)
    {
        userRepository.PushToList(userMessage);
        String BotResponse = weatherService.GetWeather(userMessage);
        return BotResponse;
    }


}
