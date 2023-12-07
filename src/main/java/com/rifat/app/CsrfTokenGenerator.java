package com.rifat.app;

import java.security.SecureRandom;
import java.util.Base64;

public class CsrfTokenGenerator {
    public static String generateCsrfToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[20];
        secureRandom.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
}
