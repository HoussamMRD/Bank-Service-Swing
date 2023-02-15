package view.Admin;

import dao.daoFiles.ClientDAO;
import metier.forms.ClientFormValidator;
import presentation.model.Client;
import presentation.model.Sexe;
import view.Panels.BottomFormPanel;
import view.Panels.ClientProfile;
import view.Panels.TableComponents.TablePanel;
import view.Panels.TitrePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditClient extends JFrame {
    ClientDAO clientDAO = new ClientDAO();
    ClientFormValidator cfm = new ClientFormValidator();
    TitrePanel topPnl = new TitrePanel("Modifier informations client");
    ClientProfile centerPnl = new ClientProfile();
    BottomFormPanel bottomPnl = new BottomFormPanel("Modifier","Annuler");
    private TablePanel testPnl;
    public EditClient(Client client, TablePanel ClientTablePnl){
        testPnl = ClientTablePnl;
        this.setTitle("Modifier client");
        this.setSize(310,360);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(topPnl, BorderLayout.NORTH);
        topPnl.setBorder(BorderFactory.createEmptyBorder(0, 0, 17, 0));

        add(centerPnl,BorderLayout.CENTER);
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

        add(bottomPnl,BorderLayout.SOUTH);
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
                            if(clientDAO.update(client)!=null){
                                JOptionPane.showMessageDialog(null,"Modification effectuee avec succes !");
                                testPnl.getTableModel().initClientsData(new ClientDAO().findAll());
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
        setVisible(true);
    }
}
