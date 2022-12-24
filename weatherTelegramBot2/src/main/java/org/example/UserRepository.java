package org.example;

import java.io.*;
import java.io.IOException;
import java.security.PublicKey;

public class UserRepository {

    String cityName;

    public void updateUserStatus(String text,String chatId)
    {
        String fileName = "UserReposytory/"+chatId;
        File file = new File(fileName);
        if(!file.exists())
        {
            writeToFile(text, chatId);
        }
        this.cityName = fileRead(chatId);
        System.out.println(cityName);

    }
    public String fileRead(String chatId) {
        String fileName = "UserReposytory/"+chatId;
        String cityName = null;
        try {
            FileReader fR = new FileReader(fileName);
            char[] buffer = new char[1000];

            int chars = fR.read(buffer);

            while (chars != -1) {
                cityName = String.valueOf(buffer, 0, chars);
                chars = fR.read(buffer);
            }
            fR.close();

            // отлавливаем исключение
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityName;
    }

    public void writeToFile(String text, String chatId)
    {
        String fileName = "UserReposytory/"+chatId;
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(fileName,true));
            pw.println(text);
            pw.close();
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
