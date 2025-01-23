package com.example.Objects;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PasswordTableModel extends AbstractTableModel {
    private List<Password> passwords;
    private String[] columnNames = {"Password", "Application"};

    public PasswordTableModel() {
        this.passwords = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return passwords.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override 
    public Object getValueAt(int rowIndex, int columnIndex) {
        Password password = passwords.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return password.getPassword();
            case 1:
                return password.getApp();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    // Přidání metody addPassword() pro přidání nového hesla
    public void addPassword(Password password) {
        passwords.add(password);
        fireTableRowsInserted(passwords.size() - 1, passwords.size() - 1); // Aktualizace tabulky
    }

    public void removeRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < passwords.size()) {
            passwords.remove(rowIndex);
            fireTableRowsDeleted(rowIndex, rowIndex); // Aktualizace tabulky
        }
    }

    public Password getPasswordAt(int rowIndex) {
        return passwords.get(rowIndex);
    }
}