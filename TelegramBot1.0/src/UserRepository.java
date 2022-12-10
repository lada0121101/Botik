package org.example;

import java.io.*;

public class UserRepository {

    UserRepository(String userId, String userMessage) {

        fileCreation(userId);
        writeToFile(userMessage, userId);
    }

    private void fileCreation(String fileName) {
        try {
            File file = new File("UserReposytory", fileName);
            if (!file.exists())
                file.createNewFile();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeToFile(String text, String userId)
    {
        String fileName = "UserReposytory/"+userId;
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