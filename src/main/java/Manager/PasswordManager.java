package Manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import Authentication.Email;
import Authentication.Password;
import Authentication.User;

import Generators.EmailGenerator;
import Generators.PasswordGenerator;
import Generators.UsernameGenerator;

public class PasswordManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, User> userMap = new HashMap<>();
        HashSet<String> usernameSet = new HashSet<>();
        HashSet<String> emailSet = new HashSet<>();

        System.out.println("Welcome to OnePass!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Log in");
            System.out.println("2. Sign up");
            System.out.println("3. Forgot password");
            System.out.println("4. Forgot username");
            System.out.println("5. Quit");
            System.out.println("6. Send credentials to files");
            System.out.println("7. View all stored credentials (Admin only)");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (choice == 1) {
                // Log in
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                
                User user = userMap.get(username);
                if (user != null && user.getPassword().equals(password)) {
                    System.out.println("Logged in successfully!");
                    // Add any functionality you want to perform after successful login
                } else {
                    System.out.println("Invalid username or password.");
                }

            } else if (choice == 2) {
                // Sign up

                // Username
                System.out.println("Do you want to use a custom username or generate a random one?");
                System.out.println("1. Custom username");
                System.out.println("2. Random username");
                String username;
                int usernameChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (usernameChoice == 1) {
                    System.out.print("Enter a new username: ");
                    username = scanner.nextLine();

                    if (usernameSet.contains(username)) {
                        System.out.println("Username already exists. Please choose a different username.");
                        continue;
                    }
                } else if (usernameChoice == 2) {
                    do {
                        username = UsernameGenerator.generateUsername();
                    } while (usernameSet.contains(username));
                } else {
                    System.out.println("Invalid choice. Returning to main menu.");
                    continue;
                }
                usernameSet.add(username);

                // Email
                System.out.println("Do you want to use a custom email or generate a random one?");
                System.out.println("1. Custom email");
                System.out.println("2. Random email");
                String email;
                int emailChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (emailChoice == 1) {
                    System.out.print("Enter a new email: ");
                    email = scanner.nextLine();

                    if (emailSet.contains(email)) {
                        System.out.println("Email already exists. Please choose a different email.");
                        continue;
                    }
                } else if (emailChoice == 2) {
                    do {
                        email = EmailGenerator.generateEmail(username);
                    } while (emailSet.contains(email));
                } else {
                    System.out.println("Invalid choice. Returning to main menu.");
                    continue;
                }
                emailSet.add(email);

                // Password
                System.out.println("Do you want to use a custom password or generate a random one?");
                System.out.println("1. Custom password");
                System.out.println("2. Random password");
                String password;
                int passwordChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                if (passwordChoice == 1) {
                    System.out.print("Enter a new password: ");
                    password = scanner.nextLine();
                } else if (passwordChoice == 2) {
                    password = PasswordGenerator.generatePassword();
                } else {
                    System.out.println("Invalid choice. Returning to main menu.");
                    continue;
                }

                User newUser = new User(username, new Password(password), new Email(email));
                userMap.put(username, newUser);

                System.out.println("Account created successfully! You can now log in with your new credentials.");
                System.out.println("Make sure to remember your password or else you may have to restart the process.");

            } else if (choice == 3) {
                // Forgot password
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                User user = userMap.get(username);

                if (user != null) {
                    System.out.println("Your email address is: " + user.getEmail());
                } else {
                    System.out.println("Username not found.");
                }

            } else if (choice == 4) {
                // Forgot username
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                String username = null;

                for (User user : userMap.values()) {
                    if (user.getEmail().equals(email)) {
                        username = user.getUsername();
                        break;
                    }
                }

                if (username != null) {
                    System.out.println("Your username is: " + username);
                } else {
                    System.out.println("Email not found.");
                }

            } else if (choice == 5) {
                break;

            }  else if(choice == 6){
                String username = null;
                Object password = null;
                String email = null;
               
                for (User user : userMap.values()) {
                   username = user.getUsername();
                   password = user.getPassword();
                   email = user.getEmail();
                }

                
                if (username != null && password != null && email != null) {
                    try {
                        FileWriter writer = new FileWriter(username + ".txt");
                        writer.write("Username: " + username + "\n");
                        writer.write("Password: " + password.toString() + "\n");
                        writer.write("Email: " + email + "\n");
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Credentials sent to files");
            }
            else if (choice == 7) {
                // View all stored credentials (Admin only)
                System.out.print("Enter admin password: ");
                String adminPassword = scanner.nextLine();

                if (adminPassword.equals("King")) {
                    System.out.println("Stored credentials:");
                    for (User user : userMap.values()) {
                        System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword() + ", Email: " + user.getEmail());
                    }
                } else {
                    System.out.println("Incorrect admin password.");
                }

            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}