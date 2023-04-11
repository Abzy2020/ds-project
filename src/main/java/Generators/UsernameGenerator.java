package Generators;

import java.security.SecureRandom;

public class UsernameGenerator {

    private static final String CHAR_POOL_RANDOM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String CHAR_POOL_CUSTOM = "0123456789";
    private static final int USERNAME_LENGTH = 8;
    private static final SecureRandom RANDOM = new SecureRandom();

    // for custom usernames
    public static String generateUsername(String user) {
        StringBuilder username = new StringBuilder(USERNAME_LENGTH);
        for (int i = 0; i < USERNAME_LENGTH; i++) {
            username.append(CHAR_POOL_CUSTOM.charAt(RANDOM.nextInt(CHAR_POOL_CUSTOM.length())));
        }
        return user + username.toString();
    }

    // for random usernames
    public static String generateUsername() {
        StringBuilder username = new StringBuilder(USERNAME_LENGTH);
        for (int i = 0; i < USERNAME_LENGTH; i++) {
            username.append(CHAR_POOL_RANDOM.charAt(RANDOM.nextInt(CHAR_POOL_RANDOM.length())));
        }
        return username.toString();
    }
}
