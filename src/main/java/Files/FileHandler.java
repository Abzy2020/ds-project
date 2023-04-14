package Files;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;


import Authentication.User;

public class FileHandler {
    // ... (other methods)

   
    public static void writeCredentialsToFile(Map<String, User> userMap, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            for (User user : userMap.values()) {
                bw.write("Username: " + user.getUsername() + "\n");
                bw.write("Password: " + user.getPassword().toString() + "\n");
                bw.write("Email: " + user.getEmail() + "\n\n");
            }
            System.out.println("Credentials sent to file");
        } catch (IOException e) {
            System.err.println("Error writing credentials to file: " + e.getMessage());
        }
    }
    
    public static void searchCredentialsByUsername(String username, String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    String currentUsername = line.substring("Username: ".length()).trim();
                    if (currentUsername.equals(username)) {
                        found = true;
                        System.out.println("Credentials found for username " + username + ":");
                        System.out.println("Password: " + br.readLine().substring("Password: ".length()));
                        System.out.println("Email: " + br.readLine().substring("Email: ".length()) + "\n");
                        break;
                    }
                }
            }
            if (!found) {
                System.out.println("Credentials not found for username " + username);
            }
        } catch (IOException e) {
            System.err.println("Error reading credentials from file: " + e.getMessage());
        }
    }
}

