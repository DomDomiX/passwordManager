package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import com.example.Objects.PasswordUtils;

public class LoginFrame extends JFrame {

    private static final String FILE_PATH = "password.dat"; // Soubor pro uložení šifrovaného hesla

    public LoginFrame() {
        // Nastavení okna
        setTitle("Přihlášení");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel pro komponenty
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1)); // Mřížkový layout pro textové pole, heslo a tlačítka

        // Přidání komponent
        JLabel passwordLabel = new JLabel("Zadejte heslo:");
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        panel.add(passwordField);

        // Tlačítko pro přihlášení
        JButton loginButton = new JButton("Přihlásit");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredPassword = new String(passwordField.getPassword());

                // Ověření hesla s uloženým šifrovaným heslem
                if (isPasswordCorrect(enteredPassword)) {
                    System.out.println("Přihlášení úspěšné.");
                    dispose(); // Zavřít přihlašovací okno
                    // Můžete přidat kód pro otevření hlavního okna aplikace
                    new MainFrame();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Chybné heslo.");
                }
            }
        });
        panel.add(loginButton);

        // Tlačítko pro registraci (uložení hesla)
        JButton registerButton = new JButton("Registrovat");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Otevření okna pro registraci hesla
                new RegisterFrame();
            }
        });
        panel.add(registerButton);

        // Přidání panelu do okna
        add(panel);

        setVisible(true); // Zobrazení okna
    }

    // Metoda pro ověření, zda je heslo správné
    private boolean isPasswordCorrect(String enteredPassword) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String storedHash = reader.readLine(); // Přečte uložené heslo (šifrované)
            reader.close();
            return PasswordUtils.checkPassword(enteredPassword, storedHash); // Ověří, zda zadané heslo odpovídá šifrovanému
        } catch (IOException e) {
            // Pokud soubor neexistuje, znamená to, že heslo ještě nebylo nastaveno
            return false;
        }
    }

    public static void main(String[] args) {
        new LoginFrame(); // Spuštění login okna
    }
}
