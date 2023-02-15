package view.Admin;

import dao.daoFiles.ClientDAO;
import dao.daoFiles.CompteDAO;
import view.Panels.BottomFormPanel;
import view.Panels.InfosPanel;
import view.Panels.TitrePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Statistiques extends JFrame {
    ClientDAO clientDAO = new ClientDAO();
    CompteDAO compteDAO = new CompteDAO();
    TitrePanel topPnl = new TitrePanel("Statistiques generales");
    InfosPanel centerPnl = new InfosPanel("Statistiques generales");
    BottomFormPanel botoomPnl = new BottomFormPanel("OK");
    public Statistiques(){
        this.setSize(260,450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        this.setLocationRelativeTo(null);
        add(topPnl, BorderLayout.NORTH);
        topPnl.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        centerPnl.getTxt1().setText(String.valueOf(clientDAO.findAll().size()));
        centerPnl.getTxt2().setText(clientDAO.calculerEtAfficherStatistiques().getNomClientLePlusRiche());
        centerPnl.getTxt3().setText(String.valueOf(clientDAO.calculerEtAfficherStatistiques().getTotaleClientsHomme()));
        centerPnl.getTxt4().setText(String.valueOf(clientDAO.calculerEtAfficherStatistiques().getTotalClientsFemme()));
        centerPnl.getTxt5().setText(String.valueOf(compteDAO.findAll().size()));
        centerPnl.getTxt6().setText(String.valueOf(clientDAO.calculerEtAfficherStatistiques().getMaxSolde())+" DH");
        centerPnl.getTxt7().setText(String.valueOf(clientDAO.calculerEtAfficherStatistiques().getMinSolde())+" DH");
        add(centerPnl,BorderLayout.CENTER);
        botoomPnl.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(botoomPnl,BorderLayout.SOUTH);
        setVisible(true);
    }
}
