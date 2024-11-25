package com.example.Objects;

public class Password {
    private String password;
    private String app;
    
    public Password(String password, String app) {
        this.password = password;
        this.app = app;
    }

    public String getPassword() {
        return password;
    }

    public String getApp() {
        return app;
    }
}

