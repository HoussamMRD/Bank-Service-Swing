package view.Panels;

import javax.swing.*;
import java.awt.*;

public class ClientProfile extends JPanel {
    private JTextField log,mdp,nom,prenom,cin,email,tel;
    private JComboBox sexCombo;
    private JLabel lblLog,lblMdp,lblNom,lblPrenom,lblCIN,lblEmail,lblTel,lblSexe;

    public JTextField getLog() {
        return log;
    }

    public JTextField getMdp() {
        return mdp;
    }

    public JTextField getNom() {
        return nom;
    }

    public JTextField getPrenom() {
        return prenom;
    }

    public JTextField getCin() {
        return cin;
    }

    public JTextField getEmail() {
        return email;
    }

    public JTextField getTel() {
        return tel;
    }

    public JComboBox getSexCombo() {
        return sexCombo;
    }

    public ClientProfile(){
        lblLog = new JLabel("Username :     ");
        add(lblLog);
        lblLog.setForeground(Color.WHITE);
        log = new JTextField(20);
        log.setHorizontalAlignment(SwingConstants.CENTER);
        add(log);
        lblMdp = new JLabel("Mot de passe :");
        add(lblMdp);
        lblMdp.setForeground(Color.WHITE);
        mdp = new JTextField(20);
        mdp.setHorizontalAlignment(SwingConstants.CENTER);
        add(mdp);
        lblNom = new JLabel("Nom :               ");
        add(lblNom);
        lblNom.setForeground(Color.WHITE);
        nom = new JTextField(20);
        nom.setHorizontalAlignment(SwingConstants.CENTER);
        add(nom);
        lblPrenom = new JLabel("Prenom :          ");
        add(lblPrenom);
        lblPrenom.setForeground(Color.WHITE);
        prenom = new JTextField(20);
        prenom.setHorizontalAlignment(SwingConstants.CENTER);
        add(prenom);
        lblCIN = new JLabel("CIN :                 ");
        add(lblCIN);
        lblCIN.setForeground(Color.WHITE);
        cin = new JTextField(20);
        cin.setHorizontalAlignment(SwingConstants.CENTER);
        add(cin);
        lblEmail = new JLabel("Email :              ");
        add(lblEmail);
        lblEmail.setForeground(Color.WHITE);
        email = new JTextField(20);
        email.setHorizontalAlignment(SwingConstants.CENTER);
        add(email);
        lblTel = new JLabel("Telephone :     ");
        add(lblTel);
        lblTel.setForeground(Color.WHITE);
        tel = new JTextField(20);
        tel.setHorizontalAlignment(SwingConstants.CENTER);
        add(tel);
        lblSexe = new JLabel("Sexe :           ");
        add(lblSexe);
        lblSexe.setForeground(Color.WHITE);
        String[] sex={"HOMME","FEMME"};
        sexCombo= new JComboBox(sex);
        add(sexCombo);
        setBackground(new Color(102, 255, 0));
    }
}
