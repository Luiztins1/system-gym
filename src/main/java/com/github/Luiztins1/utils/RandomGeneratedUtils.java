package com.github.Luiztins1.utils;

import java.security.SecureRandom;

public class RandomGeneratedUtils {

    private final static String ALPHA_NUMERIC_STRING =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                    + "0123456789"
                    + "abcdefghijklmnopqrstuvwxyz"
                    +"!@#";
    private final static SecureRandom random = new SecureRandom();

    public static String generateLogin(int n){
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < n; i++){
            int index = random.nextInt(ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(index));
        }

        return builder.toString();
    }

    public static String generatePassword(int n){
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < n; i++){
            int index = random.nextInt(ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(index));
        }
        int password = 100 + random.nextInt(900);

        return (password) + builder.toString();
    }
}
