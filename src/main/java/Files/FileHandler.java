package Files;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileHandler {
    // ... (other methods)

    public static void writeUsernamesToFile(Set<String> usernames, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String username : usernames) {
                bw.write(username);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing usernames to file: " + e.getMessage());
        }
    }

    public static Set<String> readUsernamesFromFile(String filename) {
        Set<String> usernames = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String username;
            while ((username = br.readLine()) != null) {
                usernames.add(username);
            }
        } catch (IOException e) {
            System.err.println("Error reading usernames from file: " + e.getMessage());
        }

        return usernames;
    }
}

