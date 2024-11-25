package GUI;

import com.example.Objects.Password;
import com.example.Objects.PasswordTableModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPassword extends JFrame{
    private JTextField passwordField;
    private JTextField appField;
    
    public AddPassword(PasswordTableModel tableModel) {
        // Nastavení okna
        setTitle("Přidat heslo");
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Vytvoření panelu
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2)); // Mřížkový layout pro pole

        // Přidání komponent
        panel.add(new JLabel("Password:"));
        passwordField = new JTextField();
        panel.add(passwordField);

        panel.add(new JLabel("App:"));
        appField = new JTextField();
        panel.add(appField);

        // Přidání tlačítka Potvrdit
        JButton confirmButton = new JButton("Potvrdit");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = passwordField.getText(); // Získání textu z pole
                String app = appField.getText();
                System.out.println("Heslo bylo uloženo: " + password + "Applikace: " + app); // Můžete nahradit logiku pro uložení hesla

                tableModel.addPassword(new Password(password, app));

                dispose(); // Zavření okna
            }
        });
        
        panel.add(confirmButton);

        // Přidání tlačítka Zrušit
        JButton cancelButton = new JButton("Zrušit");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Zavření okna
            }
        });
        panel.add(cancelButton);

        // Přidání panelu do okna
        add(panel);
        
        setVisible(true); // Zobrazení okna
    }
}