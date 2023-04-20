package Generators;

import java.util.Random;

public class EmailGenerator {
    //Pre-determined email domains
    private static final String[] DOMAINS = {"example.com", "gmail.com", "inbox.com", "email.net"};
    //Pre-determined characters for email
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    //Length of local part of email
    private static final int LOCAL_PART_LENGTH = 10;

    //Generates email based on username
    public static String generateEmail(String username) {
        //Creates a random object
        Random random = new Random();
        //StringBuilder for local part of email address
        StringBuilder localPart = new StringBuilder(LOCAL_PART_LENGTH);
        // generates a random character for each postion in the local part of the email address
        for (int i = 0; i < LOCAL_PART_LENGTH; i++) {
            localPart.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        // Chooses a random domain from the array of domains
        String domain = DOMAINS[random.nextInt(DOMAINS.length)];
        // concatenates the local part and domain to create the email address
        return username + "@" + domain;
    }
} 

