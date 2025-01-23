package com.example.Objects;

import javax.crypto.SecretKey;

public class Password {
    private String password;
    private String app;
    private SecretKey secretKey; 
    
    public Password(String password, String app, SecretKey secretKey) {
        this.password = password;
        this.app = app;
        this.secretKey = secretKey;
    }

    public String getPassword() {
        return password;
    }

    public String getApp() {
        return app;
    }

    public SecretKey getSecretKey() { // Přidáno
        return secretKey;
    }
}

