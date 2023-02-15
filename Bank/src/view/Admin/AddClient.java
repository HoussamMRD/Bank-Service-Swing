package view.Admin;

import dao.daoFiles.ClientDAO;
import dao.daoFiles.FileBasePaths;
import metier.forms.ClientFormValidator;
import presentation.model.Client;
import presentation.model.Sexe;
import presentation.model.TypeLog;
import view.Panels.BottomFormPanel;
import view.Panels.ClientProfile;
import view.Panels.TableComponents.TablePanel;
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

public class AddClient extends JFrame {
    ClientDAO clientDAO = new ClientDAO();
    ClientFormValidator cfm = new ClientFormValidator();
    ClientProfile centerPnl;
    BottomFormPanel bottomPnl;
    TablePanel testPnl;
//    JFrame testFrame;
    public BottomFormPanel getBottomPnl() {
        return bottomPnl;
    }

    public AddClient(TablePanel ClientTablePnl){
        testPnl = ClientTablePnl;
//        testFrame = TbCl;
        this.setTitle("Ajouter client");
        this.setSize(310,360);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        TitrePanel topPanel = new TitrePanel("Ajouter nouveau client");
        add(topPanel, BorderLayout.NORTH);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 17, 0));

        centerPnl = new ClientProfile();
        add(centerPnl,BorderLayout.CENTER);

        bottomPnl = new BottomFormPanel("Ajouter","Annuler");
        bottomPnl.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!centerPnl.getLog().getText().isEmpty() && !centerPnl.getMdp().getText().isEmpty() && !centerPnl.getNom().getText().isEmpty() && !centerPnl.getPrenom().getText().isEmpty() && !centerPnl.getCin().getText().isEmpty() && !centerPnl.getEmail().getText().isEmpty() && !centerPnl.getTel().getText().isEmpty()){
                    if(cfm.createSession(centerPnl.getLog().getText(),centerPnl.getMdp().getText(),centerPnl.getNom().getText(),
                            centerPnl.getPrenom().getText(),centerPnl.getCin().getText(),
                            centerPnl.getEmail().getText(),centerPnl.getTel().getText())!=null){
                        Client cl;
                        if(clientDAO.findByLogAndPass(centerPnl.getLog().getText(),centerPnl.getMdp().getText())==null && clientDAO.findByCIN(centerPnl.getCin().getText())==null){
                            if(centerPnl.getSexCombo().getSelectedItem().equals("HOMME")){
                                cl=cfm.createSession(centerPnl.getLog().getText(),centerPnl.getMdp().getText(),centerPnl.getNom().getText(),
                                        centerPnl.getPrenom().getText(),centerPnl.getCin().getText(),
                                        centerPnl.getEmail().getText(),centerPnl.getTel().getText());
                                cl.setSexe(Sexe.HOMME);
                            }
                            else{
                                cl=cfm.createSession(centerPnl.getLog().getText(),centerPnl.getMdp().getText(),centerPnl.getNom().getText(),
                                        centerPnl.getPrenom().getText(),centerPnl.getCin().getText(),
                                        centerPnl.getEmail().getText(),centerPnl.getTel().getText());
                                cl.setSexe(Sexe.FEMME);
                            }
                            if(clientDAO.save(cl)!=null){
                                String clientNBR = "client_ID";
                                clientNBR = clientNBR.concat(String.valueOf(cl.getId()));
                                clientNBR = clientNBR.concat(".txt");
                                Path p = FileBasePaths.createClienLogs(clientNBR);
                                String logClt = TypeLog.CREATION.toString() + "\t\t" + "Client cree le "+ LocalDate.now()+"\n";
                                try {
                                    Files.writeString(p, logClt, StandardOpenOption.APPEND);
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                                testPnl.getTableModel().initClientsData( new ClientDAO().findAll());
                                dispose();
//                                testFrame.dispose();
//                                TableClients tableClients = new TableClients();
//                                tableClients.setVisible(true);
                            }
                        }
                        else
                            JOptionPane.showMessageDialog(null,"Error : Un autre client possede ces informations. Reessayez !");
                    }else
                        JOptionPane.showMessageDialog(null, "Error : Tous les champs doivent etre valides !");
                }else
                    JOptionPane.showMessageDialog(null, "Error : Tous les champs doivent etre remplis !");
            }
        });
        bottomPnl.getNoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(bottomPnl,BorderLayout.SOUTH);
        setVisible(true);
    }
}
