package Files;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;


import Authentication.User;

public class FileManager {

    public static void writeCredentialsToFile(Map<String, User> userMap) {
        try (BufferedReader br = new BufferedReader(new FileReader("credentials.txt"))) {
            Set<String> existingUsernames = new HashSet<>();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    String username = line.substring("Username: ".length()).trim();
                    existingUsernames.add(username);
                }
            }
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("credentials.txt",true))) {
                for (User user : userMap.values()) {
                    String username = user.getUsername();
                    if (!existingUsernames.contains(username)) { // check if user has been deleted
                        if (existingUsernames.contains(username)) {
                            System.out.println("Credentials already exist for username " + username);
                            continue;
                        }
                        bw.write("Username: " + username + "\n");
                        bw.write("Password: " + user.getPassword().toString() + "\n");
                        bw.write("Email: " + user.getEmail() + "\n\n");
                        System.out.println("Credentials added for username " + username);
                    }
                }
                System.out.println("Credentials sent to file");
            } catch (IOException e) {
                System.err.println("Error writing credentials to file: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println("Error reading credentials from file: " + e.getMessage());
        }
    }
    
    public static void deleteCredentialsByUsernameAndPassword(String username, String password) {
        try {
            // Create a temporary file to write the new credentials to
            File tempFile = new File("temp_credentials.txt");
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            Set<String> deletedUsers = new HashSet<>();
            // Read the credentials from the file, skipping the ones for the user to be deleted
            boolean found = false;
            try (BufferedReader br = new BufferedReader(new FileReader("credentials.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("Username: ")) {
                        String currentUsername = line.substring("Username: ".length()).trim();
                        if (currentUsername.equals(username)) {
                            // Check if the password matches
                            String currentPassword = br.readLine().substring("Password: ".length()).trim();
                            if (!currentPassword.equals(password)) {
                                System.out.println("Incorrect password for username " + username);
                                bw.close();
                                return;
                            }
                            found = true;
                            br.readLine(); // Skip the email line
                        } else {
                            bw.write(line + "\n");
                            bw.write(br.readLine() + "\n");
                            bw.write(br.readLine() + "\n");
                        }
                    }
                }
            }
    
            if (!found) {
                System.out.println("Credentials not found for username " + username);
                bw.close();
                return;
            }
    
            // Close the temporary file
            bw.close();
    
            // Delete the original file and rename the temporary file
            File origFile = new File("credentials.txt");
            if (origFile.delete()) {
                if (!tempFile.renameTo(origFile)) {
                    System.err.println("Error renaming temp file to " + origFile.getName());
                }
            } else {
                System.err.println("Error deleting file " + origFile.getName());
            }
    
            // Add the username to the set of deleted users
            deletedUsers.add(username);
            
            System.out.println("Credentials deleted for username " + username);
    
        } catch (IOException e) {
            System.err.println("Error deleting credentials: " + e.getMessage());
        }
    }
    
    public static boolean canAddUser(String username) {
        Set<String> deletedUsers = new HashSet<>();
        return !deletedUsers.contains(username);
    }
}
