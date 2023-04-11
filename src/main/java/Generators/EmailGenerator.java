package Generators;

import java.util.Random;

public class EmailGenerator {
    private static final String[] DOMAINS = {"example.com", "mail.com", "inbox.com", "email.net"};
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LOCAL_PART_LENGTH = 10;

    public static String generateEmail(String username) {
        Random random = new Random();
        StringBuilder localPart = new StringBuilder(LOCAL_PART_LENGTH);

        for (int i = 0; i < LOCAL_PART_LENGTH; i++) {
            localPart.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        String domain = DOMAINS[random.nextInt(DOMAINS.length)];
        return localPart.toString() + "@" + domain;
    }
} 

