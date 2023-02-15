package view.Client;

import dao.daoFiles.ClientDAO;
import metier.forms.ClientFormValidator;
import presentation.model.Client;
import presentation.model.Sexe;
import view.Panels.BottomFormPanel;
import view.Panels.ClientProfile;
import view.Panels.TitrePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifierDonnees extends JFrame {
    ClientFormValidator cfm = new ClientFormValidator();
    ClientDAO clientDAO = new ClientDAO();
    ClientProfile centerPnl;

    public ModifierDonnees(Client client){
        this.setTitle("Modifier vos informations");
        this.setSize(310,360);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        TitrePanel topPanel = new TitrePanel("Modifier vos informations");
        add(topPanel,BorderLayout.NORTH);
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 17, 0));

        centerPnl = new ClientProfile();
        centerPnl.getLog().setText(client.getLogin());
        centerPnl.getMdp().setText(client.getMotDePasse());
        centerPnl.getNom().setText(client.getNom());
        centerPnl.getPrenom().setText(client.getPrenom());
        centerPnl.getCin().setText(client.getCin());
        centerPnl.getCin().setEnabled(false);
        centerPnl.getCin().setDisabledTextColor(Color.BLACK);
        centerPnl.getEmail().setText(client.getEmail());
        centerPnl.getTel().setText(client.getTel());
        centerPnl.getSexCombo().removeAllItems();
        centerPnl.getSexCombo().addItem(client.getSexe());
        if(client.getSexe().equals(Sexe.HOMME))
            centerPnl.getSexCombo().addItem(Sexe.FEMME);
        else
            centerPnl.getSexCombo().addItem(Sexe.HOMME);
        add(centerPnl,BorderLayout.CENTER);

        BottomFormPanel bottomPnl = new BottomFormPanel("Modifier","Annuler");
        bottomPnl.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!centerPnl.getLog().getText().isEmpty() && !centerPnl.getMdp().getText().isEmpty() && !centerPnl.getNom().getText().isEmpty() && !centerPnl.getPrenom().getText().isEmpty() && !centerPnl.getEmail().getText().isEmpty() && !centerPnl.getTel().getText().isEmpty()){
                    if(cfm.EditClient(centerPnl.getLog().getText(),centerPnl.getMdp().getText(),centerPnl.getNom().getText(),centerPnl.getPrenom().getText(),centerPnl.getCin().getText(),centerPnl.getEmail().getText(),centerPnl.getTel().getText())){
                        if(clientDAO.DoublonsByLogAndPass(client,centerPnl.getLog().getText(),centerPnl.getMdp().getText())==null){
                            client.setLogin(centerPnl.getLog().getText());
                            client.setMotDePasse(centerPnl.getMdp().getText());
                            client.setNom(centerPnl.getNom().getText());
                            client.setPrenom(centerPnl.getPrenom().getText());
                            client.setCin(centerPnl.getCin().getText());
                            client.setEmail(centerPnl.getEmail().getText());
                            client.setTel(centerPnl.getTel().getText());
                            client.setSexe((Sexe) centerPnl.getSexCombo().getSelectedItem());
//                            if(centerPnl.getSexCombo().getSelectedItem().equals("HOMME"))
//                                client.setSexe(Sexe.HOMME);
//                            else
//                                client.setSexe(Sexe.FEMME);
                            if(clientDAO.update(client)!=null){
                                JOptionPane.showMessageDialog(null,"Modification effectuee avec succes !");
                                dispose();
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
