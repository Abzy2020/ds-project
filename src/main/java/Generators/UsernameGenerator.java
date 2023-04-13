package Generators;

import java.security.SecureRandom;

public class UsernameGenerator {

    private static final String[] ADJECTIVE_POOL = {"Wonderful", "Cool", "Weird", "Crazy", "Awesome", "Amazing", "Funny", "Smart", "Brilliant", "Genous"};
    private static final String[] NOUN_POOL = {"Dog", "Cat", "Eagle", "Dolphin", "Lion", "Tiger", "Bear", "Elephant", "Penguin", "Panda"};
    private static final String CHAR_POOL_CUSTOM = "0123456789";
    private static final int USERNAME_LENGTH = 8;
    private static final SecureRandom RANDOM = new SecureRandom();

    // Generates username based on user input
    public static String generateUsername(String user) {
        StringBuilder username = new StringBuilder(USERNAME_LENGTH);
        for (int i = 0; i < USERNAME_LENGTH; i++) {
            username.append(CHAR_POOL_CUSTOM.charAt(RANDOM.nextInt(CHAR_POOL_CUSTOM.length())));
        }
        return user + username.toString();
    }

    // Generates random usernames using random adjectives and nouns
    public static String generateUsername() {
        StringBuilder username = new StringBuilder(USERNAME_LENGTH);
        for (int i = 0; i < USERNAME_LENGTH/2; i++) {
            username.append(CHAR_POOL_CUSTOM.charAt(RANDOM.nextInt(CHAR_POOL_CUSTOM.length())));
        }
        return ADJECTIVE_POOL[RANDOM.nextInt(ADJECTIVE_POOL.length)] + NOUN_POOL[RANDOM.nextInt(NOUN_POOL.length)] + username.toString();
    }
}
