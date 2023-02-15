package view.Client;

import dao.daoFiles.CompteDAO;
import dao.daoFiles.FileBasePaths;
import presentation.model.Compte;
import presentation.model.TypeLog;
import view.Panels.BottomFormPanel;
import view.Panels.CompteOpPanel;
import view.Panels.TitrePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class Verser extends JFrame {
    CompteDAO cpDAO = new CompteDAO();
    public Verser(Compte compte){
        setTitle("Versement");
        this.setSize(250,200);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        TitrePanel topPnl = new TitrePanel("Versement");
        add(topPnl, BorderLayout.NORTH);
        topPnl.setBorder(BorderFactory.createEmptyBorder(0, 0, 17, 0));

        CompteOpPanel centerPnl = new CompteOpPanel(TypeLog.VERSEMENT);
        add(centerPnl, BorderLayout.CENTER);

        BottomFormPanel bottomFormPanel = new BottomFormPanel("Verser","Annuler");
        bottomFormPanel.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!centerPnl.getMtField().getText().isEmpty()){
                    double mt = Double.parseDouble(centerPnl.getMtField().getText());
                    if(mt>0.0){
                        compte.setSolde(compte.getSolde()+mt);
                        cpDAO.update(compte);
                        String clientNBR = "client_ID";
                        clientNBR = clientNBR.concat(String.valueOf(compte.getProprietaire().getId()));
                        clientNBR = clientNBR.concat(".txt");
                        Path p = FileBasePaths.createClienLogs(clientNBR);
                        String logClt = TypeLog.VERSEMENT.toString() + "\t\t" + "Versement de "+
                                String.valueOf(mt)+" DH au compte numero "+compte.getNumeroCompte()+" le "+ LocalDate.now() +"\n";
                        try {
                            Files.writeString(p,logClt, StandardOpenOption.APPEND);
                        } catch (IOException exception) {
                            throw new RuntimeException(exception);
                        }
                        JOptionPane.showMessageDialog(null,"Versement effectue avec succes !");
                        dispose();
                    }
                }
            }
        });
        bottomFormPanel.getNoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Versement annule !");
                dispose();
            }
        });
        add(bottomFormPanel,BorderLayout.SOUTH);
        setVisible(true);
    }
}
