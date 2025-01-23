package GUI;

import javax.swing.*;

import com.example.Objects.PasswordUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class RegisterFrame extends JFrame {

    public RegisterFrame() {
        // Nastavení okna
        setTitle("Registrace");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel pro komponenty
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1)); // Mřížkový layout

        // Přidání komponent
        JLabel passwordLabel = new JLabel("Zadejte nové heslo:");
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);

        // Tlačítko pro registraci
        JButton registerButton = new JButton("Registrovat");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());

                // Šifrování hesla a uložení do souboru
                String hashedPassword = PasswordUtils.hashPassword(password);

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("password.dat"));
                    writer.write(hashedPassword);
                    writer.close();
                    System.out.println("Heslo bylo úspěšně uloženo.");
                    dispose(); // Zavření registračního okna
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Chyba při ukládání hesla.");
                }
            }
        });
        panel.add(registerButton);

        // Přidání panelu do okna
        add(panel);

        setVisible(true); // Zobrazení okna
    }
}
