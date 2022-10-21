import java.util.LinkedList;

public class UserRepository {

    private LinkedList<String> ListMessageUser = new LinkedList<>();

    public void PushToList(String message)
    {
        ListMessageUser.push(message);
    }
    public void PrintListMessage()
    {
        System.out.println(ListMessageUser);
    }


}
