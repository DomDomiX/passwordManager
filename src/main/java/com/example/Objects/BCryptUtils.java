package com.example.Objects;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtils {
    // Metoda pro generování hashovaného hesla
    public static String hashPassword(String password) {
        // Generování soli pro bcrypt
        String salt = BCrypt.gensalt();
        
        // Vrací hashované heslo
        return BCrypt.hashpw(password, salt);
    }
    
    // Metoda pro ověření, zda uživatelské heslo odpovídá hashovanému heslu
    public static boolean checkPassword(String password, String hashedPassword) {
        // Ověření, zda zadané heslo odpovídá hashovanému heslu
        return BCrypt.checkpw(password, hashedPassword);
    }
}
