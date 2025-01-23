package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.example.Objects.AESUtils;
import com.example.Objects.Password;
import com.example.Objects.PasswordTableModel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    private PasswordTableModel tableModel;
    
    public MainFrame() {
        // Nastavení hlavního okna
        setTitle("Password Manager");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Inicializace PasswordTableModel a tabulky
        tableModel = new PasswordTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table); // Přidání posuvníku
        add(scrollPane, BorderLayout.CENTER); // Tabulka je umístěna do středu

        // Aktualizace tabulky, aby se zobrazila nová data
        tableModel.fireTableDataChanged();

        // Vytvoření panelu pro tlačítko
        JPanel buttonPanel = new JPanel();
        
        // Vytvoření tlačítka
        JButton addButton = new JButton("Přidat heslo");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPassword(tableModel); // Otevření okna pro přidání hesla
            }
        });

        JButton deleteButton = new JButton("Smazat vybraný");
        deleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow(); // Získání vybraného řádku
            if (selectedRow != -1) {
                // Převeďte index na modelový index (v případě třídění/tabulkových změn)
                int modelRow = table.convertRowIndexToModel(selectedRow);
                tableModel.removeRow(modelRow); // Odstranění záznamu
            } else {
                JOptionPane.showMessageDialog(MainFrame.this, "Vyberte řádek, který chcete smazat.");
            }
            }
        });

        JButton showPassword = new JButton("Odhal vybraný");
        showPassword.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int modelRow = table.convertRowIndexToModel(selectedRow);
                Password selectedPassword = tableModel.getPasswordAt(modelRow);
                try {
                    String decryptedPassword = AESUtils.decrypt(
                        selectedPassword.getPassword(), 
                        selectedPassword.getSecretKey()
                    );
                    JOptionPane.showMessageDialog(
                        MainFrame.this, 
                        "Dešifrované heslo: " + decryptedPassword
                    );
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                        MainFrame.this, 
                        "Chyba při dešifrování hesla: " + ex.getMessage()
                    );
                }
            } else {
                JOptionPane.showMessageDialog(MainFrame.this, "Vyberte řádek pro odhalení hesla.");
            }
        }
    });
        
        buttonPanel.add(addButton); // Přidání tlačítka do panelu
        buttonPanel.add(deleteButton);
        buttonPanel.add(showPassword);
        add(buttonPanel, BorderLayout.SOUTH); // Vycentrované tlačítko pod tabulkou

        setVisible(true);
    }
}

