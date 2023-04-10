package Files;

import java.io.*;

import Authentication.User;

public class FileManager {

    public static void writeUserToFile(User user, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User readUserFromFile(String fileName) {
        User user = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            user = (User) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }
}
