package com.example.Objects;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Metoda pro generování šifrovaného hesla
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Metoda pro ověření hesla
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
