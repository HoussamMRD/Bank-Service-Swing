package view.Admin;

import presentation.model.Admin;
import view.Login;
import view.Panels.AdminMainPanel;
import view.Panels.BottomFormPanel;
import view.Panels.TitrePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame {
    public AdminFrame(Admin admin){
        setTitle("Services client");
        this.setSize(280,300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        TitrePanel titrePnl = new TitrePanel("Bienvenue "+admin.getNomComplet());
        add(titrePnl, BorderLayout.NORTH);
        titrePnl.setBorder(BorderFactory.createEmptyBorder(0, 0, 33, 0));

        AdminMainPanel centerPnl = new AdminMainPanel();
        add(centerPnl,BorderLayout.CENTER);
        centerPnl.getBtnClients().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TableClients();
            }
        });
        centerPnl.getBtnComptes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TableComptes();
            }
        });
        centerPnl.getBtnStat().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Statistiques();
            }
        });
        BottomFormPanel bottomPnl = new BottomFormPanel("Se deconnecter");
        bottomPnl.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });
        add(bottomPnl,BorderLayout.SOUTH);
        setVisible(true);
    }
}
