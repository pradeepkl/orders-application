package com.classpathio.ordersapplication.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderUtil {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static void main(String[] args) {
        String passwordInPlainText = "Welcome123";

        // Encode the password
        String encodedPassword1 = encodePassword(passwordInPlainText);
        String encodedPassword2 = encodePassword(passwordInPlainText);
        String encodedPassword3 = encodePassword(passwordInPlainText);
        String encodedPassword4 = encodePassword(passwordInPlainText);
        String encodedPassword5 = encodePassword(passwordInPlainText);

        System.out.println("Encoded password - 1: " + encodedPassword1);
        System.out.println("Encoded password - 2: " + encodedPassword2);
        System.out.println("Encoded password - 3: " + encodedPassword3);
        System.out.println("Encoded password - 4: " + encodedPassword4);
        System.out.println("Encoded password - 5: " + encodedPassword5);

        // Verify the password
        System.out.println("Password verification - 1: " + passwordEncoder.matches(passwordInPlainText, encodedPassword1 + "."));
        System.out.println("Password verification - 2: " + passwordEncoder.matches(passwordInPlainText, encodedPassword2));
        System.out.println("Password verification - 3: " + passwordEncoder.matches(passwordInPlainText, encodedPassword3));
        System.out.println("Password verification - 4: " + passwordEncoder.matches(passwordInPlainText, encodedPassword4));
        System.out.println("Password verification - 5: " + passwordEncoder.matches(passwordInPlainText, encodedPassword5));

    }

    private static String encodePassword(String passwordInPlainText) {
        return passwordEncoder.encode(passwordInPlainText);
    }
}
