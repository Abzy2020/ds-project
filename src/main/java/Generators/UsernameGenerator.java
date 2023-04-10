package Generators;

import java.security.SecureRandom;

public class UsernameGenerator {

    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int USERNAME_LENGTH = 8;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateUsername() {
        StringBuilder username = new StringBuilder(USERNAME_LENGTH);
        for (int i = 0; i < USERNAME_LENGTH; i++) {
            username.append(CHAR_POOL.charAt(RANDOM.nextInt(CHAR_POOL.length())));
        }
        return username.toString();
    }
}
