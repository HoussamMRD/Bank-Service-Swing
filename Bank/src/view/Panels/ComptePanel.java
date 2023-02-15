package view.Panels;

import dao.daoFiles.ClientDAO;
import presentation.model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComptePanel extends JPanel {
    private Long id;
    ClientDAO clientDAO = new ClientDAO();
    private JComboBox<String> clientJComboBox;
    private JTextField soldeTxt,txt1,txt2,txt3,txt4,txt5;
    private JLabel soldeLbl,lbl1,lbl2,lbl3,lbl4,lbl5;

    public JTextField getTxt1() {
        return txt1;
    }

    public JTextField getTxt2() {
        return txt2;
    }

    public JTextField getTxt3() {
        return txt3;
    }

    public JTextField getTxt4() {
        return txt4;
    }

    public JTextField getTxt5() {
        return txt5;
    }

    public Long getId() {
        return id;
    }

    public JTextField getSoldeTxt() {
        return soldeTxt;
    }
    public ComptePanel(boolean forCreate){
        if(forCreate){
            lbl1 = new JLabel("Selectionnez client par ID  ");
            add(lbl1);
            clientJComboBox = new JComboBox<>();
            for(Client client : clientDAO.findAll())
                clientJComboBox.addItem(String.valueOf(client.getId()));
            clientJComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object x=clientJComboBox.getSelectedItem();
                    String charId = (String) x;
                    id = Long.parseLong(charId);
                    txt1.setText(clientDAO.findById(id).getNomComplet());

                }
            });
            add(clientJComboBox);
            lbl2 = new JLabel("Nom complet du client");
            add(lbl2);
            txt1 = new JTextField(20);
            txt1.setEnabled(false);
            txt1.setHorizontalAlignment(SwingConstants.CENTER);
            txt1.setDisabledTextColor(Color.BLACK);
            add(txt1);
            soldeLbl = new JLabel("Solde initial du compte");
            add(soldeLbl);
            soldeTxt = new JTextField(20);
            soldeTxt.setHorizontalAlignment(SwingConstants.CENTER);
            add(soldeTxt);
            soldeLbl.setForeground(Color.WHITE);
            lbl1.setForeground(Color.WHITE);
            lbl2.setForeground(Color.WHITE);
        }
        else{
            lbl1 = new JLabel("Numero de compte");
            lbl1.setForeground(Color.WHITE);
            add(lbl1);
            txt1 = new JTextField(20);
            txt1.setEnabled(false);
            txt1.setHorizontalAlignment(SwingConstants.CENTER);
            txt1.setDisabledTextColor(Color.BLACK);
            add(txt1);
            lbl2 = new JLabel("Solde");
            lbl2.setForeground(Color.WHITE);
            add(lbl2);
            txt2 = new JTextField(20);
            txt2.setHorizontalAlignment(SwingConstants.CENTER);
            add(txt2);
            lbl3 = new JLabel("ID proprietaire");
            lbl3.setForeground(Color.WHITE);
            add(lbl3);
            txt3 = new JTextField(20);
            txt3.setEnabled(false);
            txt3.setHorizontalAlignment(SwingConstants.CENTER);
            txt3.setDisabledTextColor(Color.BLACK);
            add(txt3);
            lbl4 = new JLabel("Nom du proprietaire");
            lbl4.setForeground(Color.WHITE);
            add(lbl4);
            txt4 = new JTextField(20);
            txt4.setEnabled(false);
            txt4.setHorizontalAlignment(SwingConstants.CENTER);
            txt4.setDisabledTextColor(Color.BLACK);
            add(txt4);
            lbl5 = new JLabel("Date de creation");
            lbl5.setForeground(Color.WHITE);
            add(lbl5);
            txt5 = new JTextField(20);
            txt5.setEnabled(false);
            txt5.setHorizontalAlignment(SwingConstants.CENTER);
            txt5.setDisabledTextColor(Color.BLACK);
            add(txt5);
        }
        setBackground(new Color(102, 255, 0));
    }
}
