import java.util.ArrayList;
import java.util.Scanner;

public class Bot {

    public int userId;
    public String message;
    Scanner scanner1 = new Scanner(System.in);

    Bot()
    {   }
    void BotStartConsole()
    {
        while (true)
        {
            message = ReadConsole();
            BotLogics(message);
            ReplyConsole(message);
        }
    }

    void BotStartTelegram()
    {

    }

    void ReadTelegram(String message, int userId)
    {
        this.userId = userId;
    }

    String ReadConsole()
    {
        return scanner1.nextLine();
    }

    void ReplyConsole(String message)
    {
        System.out.println(message);
    }

    void BotLogics(String message)
    {
        if(message.equals("/start"))
        {
            this.message = "Привет! Я буду загадывать числа а ты отгадывать";
        }

    }

}
