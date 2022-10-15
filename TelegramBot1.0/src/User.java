import java.util.Random;

public class User {

    private String login;
    private String password;
    public String userName;
    public String userId;

    String GenerationUserId()
    {
        Random random = new Random();
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<12;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();

    }

    public int a = 0;
    User()
    {
       userId = GenerationUserId();
    }

    void NewPassword()
    {

    }

}
