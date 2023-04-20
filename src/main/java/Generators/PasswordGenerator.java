package Generators;

import java.security.SecureRandom;

public class PasswordGenerator {
    // all possible character for use in the password
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
    // length of the password
    private static final int PASSWORD_LENGTH = 12;
    private static final SecureRandom RANDOM = new SecureRandom();
    // Password generator
    public static String generatePassword() {
        // create a new StringBuilder
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        //Generate random character for each position in the password
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            password.append(CHAR_POOL.charAt(RANDOM.nextInt(CHAR_POOL.length())));
        }
        //Convert to string and return
        return password.toString();
    }
}
