package Manager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;


public class PasswordManager {

    public static void main(String[] args) {
       // See if this works for you
        Scanner scanner = new Scanner(System.in);
        HashMap<String, User> userMap = new HashMap<>();
        HashSet<String> usernameSet = new HashSet<>();
        HashSet<String> emailSet = new HashSet<>();

        // Your interaction logic with the user, for example:
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Generate random credentials");
            System.out.println("2. Create custom password");
            System.out.println("3. View stored credentials");
            System.out.println("4. Update stored credentials");
            System.out.println("5. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (choice == 1) {
                // Generate random username, password, and email
                // ... (same as before, but with added checks for uniqueness)

                String username = "";
                do {
                    username = UsernameGenerator.generateUsername();
                } while (usernameSet.contains(username));
                usernameSet.add(username);

                String email = "";
                do {
                    email = EmailGenerator.generateEmail(username);
                } while (emailSet.contains(email));
                emailSet.add(email);

                String password = PasswordGenerator.generatePassword();
                User user = new User(username, new Password(password), new Email(email));
                userMap.put(username, user);
                System.out.println("Random credentials generated and saved.");
            } else if (choice == 2) {
                // Let the user create their own password
                // ... (same as before, but with added checks for uniqueness)

                System.out.print("Enter username: ");
                String username = scanner.nextLine();

                if (usernameSet.contains(username)) {
                    System.out.println("Username already exists. Please choose a different username.");
                    continue;
                }
                usernameSet.add(username);

                System.out.print("Enter email: ");
                String email = scanner.nextLine();

                if (emailSet.contains(email)) {
                    System.out.println("Email already exists. Please choose a different email.");
                    continue;
                }
                emailSet.add(email);

                // ... (rest of the code)

            } else if (choice == 3) {
                // View stored credentials
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                User user = userMap.get(username);

                if (user != null) {
                    // ... (same as before)
                } else {
                    System.out.println("Credentials for the given username not found.");
                }
            } else if (choice == 4) {
                // Update stored credentials
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                User user = userMap.get(username);

                if (user != null) {
                    // ... (same as before)
                } else {
                    System.out.println("Credentials for the given username not found.");
                }
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
