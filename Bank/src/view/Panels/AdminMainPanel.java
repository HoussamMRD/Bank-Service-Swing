package view.Panels;

import javax.swing.*;
import java.awt.*;

public class AdminMainPanel extends JPanel {
    private JButton btnClients, btnComptes, btnStat;

    public JButton getBtnStat() {
        return btnStat;
    }

    public JButton getBtnClients() {
        return btnClients;
    }

    public JButton getBtnComptes() {
        return btnComptes;
    }

    public AdminMainPanel(){
        btnClients = new JButton("Liste des clients");
        btnClients.setPreferredSize(new Dimension(200,30));
        add(btnClients);
        btnComptes = new JButton("Liste des comptes");
        btnComptes.setPreferredSize(new Dimension(200,30));
        add(btnComptes);
        btnStat = new JButton("Statistiques generales");
        btnStat.setPreferredSize(new Dimension(200,30));
        add(btnStat);
        setBackground(new Color(102, 255, 0));
        setVisible(true);
    }
}
