package Entities.Services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveUser {

    private static final String USER_FILE_PATH = "C:\\dev\\temp\\txtfiles\\users\\";
    private static int userNumber = 1;

    public static void saveFileMethod(User user) {
        String userName = userNumber + "-" + user.getName().trim().toUpperCase().replace(" ", "");
        userNumber++;
        String userFileName = userName + ".txt";
        String userFile = USER_FILE_PATH + userFileName;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(userFile, true))) {
            bw.write(user.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
