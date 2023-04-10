package Generators;

import java.security.SecureRandom;


public class EmailGenerator {

    private static final String[] EMAIL_PROVIDERS = {"gmail.com", "yahoo.com", "outlook.com"};

    public static String generateEmail(String username) {
        String provider = EMAIL_PROVIDERS[new SecureRandom().nextInt(EMAIL_PROVIDERS.length)];
        return username + "@" + provider;
    }
}

