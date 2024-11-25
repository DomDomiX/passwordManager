package com.example;

import com.formdev.flatlaf.FlatDarkLaf;

import GUI.MainFrame;

public class Main {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        new MainFrame();
    }
}