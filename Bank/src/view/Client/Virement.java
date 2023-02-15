package view.Client;

import dao.daoFiles.ClientDAO;
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

public class Virement extends JFrame {
    private String numCP;
    private JComboBox<String> comptesCoboBox;
    CompteDAO compteDAO = new CompteDAO();
    ClientDAO clientDAO = new ClientDAO();
    public Virement(Compte compte){

        setTitle("Virement");
        this.setSize(250,260);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        TitrePanel topPnl = new TitrePanel("Virement");
        add(topPnl, BorderLayout.NORTH);
        topPnl.setBorder(BorderFactory.createEmptyBorder(0, 0, 17, 0));

        CompteOpPanel centerPnl = new CompteOpPanel(TypeLog.VIREMENT);
        JLabel clLabel=new JLabel("Numero de compte :");
        clLabel.setForeground(Color.WHITE);
        centerPnl.add(clLabel);
        comptesCoboBox=new JComboBox<>();
        comptesCoboBox.setPreferredSize(new Dimension(150,20));
        for(Compte x : compteDAO.findAll()){
            if(!x.getNumeroCompte().equals(compte.getNumeroCompte()))
                comptesCoboBox.addItem(x.getNumeroCompte());
            }
        comptesCoboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object y=comptesCoboBox.getSelectedItem();
                numCP = (String) y;
            }
        });
        centerPnl.add(comptesCoboBox);
        add(centerPnl, BorderLayout.CENTER);

        BottomFormPanel bottomFormPanel = new BottomFormPanel("Valider","Annuler");
        bottomFormPanel.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!centerPnl.getMtField().getText().isEmpty()){
                    double mt = Double.parseDouble(centerPnl.getMtField().getText());
                    if(mt>0.0 && mt<=compte.getSolde()){
                        Object y=comptesCoboBox.getSelectedItem();
                        numCP = (String) y;
                        System.out.println(numCP);
                        compte.setSolde(compte.getSolde()-mt);
                        compteDAO.update(compte);
                        String clientNBR = "client_ID";
                        clientNBR = clientNBR.concat(String.valueOf(compte.getProprietaire().getId()));
                        clientNBR = clientNBR.concat(".txt");
                        Path p = FileBasePaths.createClienLogs(clientNBR);
                        String logClt = TypeLog.VIREMENT.toString() + "\t\t" + "Virement de "+
                                String.valueOf(mt)+" DH du compte N°"+compte.getNumeroCompte()+" vers le compte N°"+
                                numCP+ " le "+ LocalDate.now() +"\n";
                        try {
                            Files.writeString(p,logClt,StandardOpenOption.APPEND);
                        } catch (IOException exception) {
                            throw new RuntimeException(exception);
                        }
                        for(Compte cc : compteDAO.findAll()){
                            if(cc.getNumeroCompte().equals(numCP)){
                                cc.setSolde(cc.getSolde()+mt);
                                compteDAO.update(cc);
                                String clientNBR1 = "client_ID";
                                clientNBR1 = clientNBR1.concat(String.valueOf(cc.getProprietaire().getId()));
                                clientNBR1 = clientNBR1.concat(".txt");
                                Path p1 = FileBasePaths.createClienLogs(clientNBR1);
                                String logClt1 = TypeLog.VIREMENT.toString() + "\t\t" + "Virement de "+
                                        String.valueOf(mt)+" DH reçu du compte N°"+compte.getNumeroCompte()+
                                        " le "+ LocalDate.now() +"\n";
                                try {
                                    Files.writeString(p1,logClt1,StandardOpenOption.APPEND);
                                } catch (IOException exception) {
                                    throw new RuntimeException(exception);
                                }
                            }
                        }
                        JOptionPane.showMessageDialog(null,"Virement effectue avec succes !");
                        dispose();
                    }
                }
            }
        });
        bottomFormPanel.getNoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Virement annule !");
                dispose();
            }
        });
        add(bottomFormPanel,BorderLayout.SOUTH);
        setVisible(true);
    }
}
