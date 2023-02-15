package view;

import dao.daoFiles.CompteDAO;
import metier.forms.LoginFormValidator;
import presentation.model.Admin;
import presentation.model.Client;
import view.Admin.AdminFrame;
import view.Client.ChoixCompte;
import view.Client.ClientFrame;
import view.Panels.BottomFormPanel;
import view.Panels.LoginFormPanel;
import view.Panels.TitrePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    CompteDAO cpDAO = new CompteDAO();
    public Login(){
        LoginFormValidator lfm = new LoginFormValidator();
        setTitle("Login");
        setSize(250,230);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        TitrePanel titrePnl = new TitrePanel("Enter your login informations");
        add(titrePnl, BorderLayout.NORTH);
        titrePnl.setBorder(BorderFactory.createEmptyBorder(0, 0, 17, 0));

        LoginFormPanel centerPnl = new LoginFormPanel();
        add(centerPnl,BorderLayout.CENTER);

        BottomFormPanel bottomPnl = new BottomFormPanel("Login","Cancel");
        bottomPnl.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String log = centerPnl.getUsernameField().getText();
                char[] pass = centerPnl.getPasswordField().getPassword();
                String mdp = new String(pass);
                if(!log.isEmpty() && !mdp.isEmpty()){
                    if(lfm.validerSession(log,mdp)!=null){
                        if(lfm.validerSession(log,mdp) instanceof Admin){
                            dispose();
                            new AdminFrame((Admin) lfm.validerSession(log,mdp));
                        }else{
                            Client c = (Client) lfm.validerSession(log,mdp);
                            if(cpDAO.findByIdProp(c.getId()).isEmpty()){
                                JOptionPane.showMessageDialog(null,"ERREUR : Vous devez tout d'abord creer un compte !");
                                centerPnl.getUsernameField().setText("");
                                centerPnl.getPasswordField().setText("");
                            }
                            else if(cpDAO.findByIdProp(c.getId()).size()==1){
                                new ClientFrame(c,cpDAO.findByIdProp(c.getId()).get(0),true);
                                dispose();
                            }
                            else{
                                new ChoixCompte(cpDAO.findByIdProp(c.getId()));
                                dispose();
                            }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Utilisateur introuvable !");
                        centerPnl.getUsernameField().setText("");
                        centerPnl.getPasswordField().setText("");
                    }
                }else
                    JOptionPane.showMessageDialog(null, "Error : all fields must be filled !");
            }
        });
        bottomPnl.getNoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                centerPnl.getUsernameField().setText("");
                centerPnl.getPasswordField().setText("");
            }
        });
        add(bottomPnl,BorderLayout.SOUTH);
        setVisible(true);
    }
}
