package view.Client;

import dao.daoFiles.ClientDAO;
import dao.daoFiles.CompteDAO;
import presentation.model.Client;
import presentation.model.Compte;
import view.Login;
import view.Panels.BottomFormPanel;
import view.Panels.ClientMainPanel;
import view.Panels.TitrePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrame extends JFrame {
    ClientDAO clientDAO = new ClientDAO();
    CompteDAO compteDAO = new CompteDAO();
    ClientMainPanel centerPnl;
    public ClientFrame(Client client, Compte compte,boolean hasOneAccount){
        setTitle("Services client");
        this.setSize(280,360);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        TitrePanel titrePnl = new TitrePanel("Bienvenu "+client.getNomComplet());
        add(titrePnl, BorderLayout.NORTH);
        titrePnl.setBorder(BorderFactory.createEmptyBorder(0, 0, 17, 0));

        centerPnl = new ClientMainPanel(hasOneAccount);
        add(centerPnl,BorderLayout.CENTER);
        centerPnl.getBtnVersement().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Verser(compte);
            }
        });
        centerPnl.getBtnRetrait().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Retirer(compte);
            }
        });
        centerPnl.getBtnVirement().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Virement(compte);
            }
        });
        centerPnl.getBtnMod().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ModifierDonnees(client);
            }
        });
        centerPnl.getBtnInfo().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Infos(compte);
            }
        });
        if(!hasOneAccount)
            centerPnl.getBtnChCompte().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ChoixCompte(compteDAO.findByIdProp(client.getId()));
                    dispose();
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
