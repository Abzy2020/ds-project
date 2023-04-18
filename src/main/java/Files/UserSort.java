package Files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import Authentication.Email;
import Authentication.Password;
import Authentication.User;
public class UserSort {
   
    public static void sortUser(Map<String, User> userMap) throws FileNotFoundException, IOException {
        // Create a TreeMap to store the sorted entries
        Map<String, User> sortedMap = new TreeMap<>();
        
        // Read the credentials from the file and add them to the sortedMap
        try (BufferedReader br = new BufferedReader(new FileReader("credentials.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    String username = line.substring("Username: ".length()).trim();
                    String password = br.readLine().substring("Password: ".length()).trim();
                    String email = br.readLine().substring("Email: ".length()).trim();
                    User user = new User(username, new Password(password), new Email(email));
                    sortedMap.put(username, user);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading credentials from file: " + e.getMessage());
        }
        
        // Print the sorted entries 

        
        for(User user :sortedMap.values()) {
            System.out.println(user.getUserInfo());
        }
    }  


}
   


