package view.Panels;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class InfosPanel extends JPanel {
    private JTextField txt1,txt2,txt3,txt4,txt5,txt6,txt7;
    private JLabel lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7;

    public JTextField getTxt3() {
        return txt3;
    }

    public JTextField getTxt4() {
        return txt4;
    }

    public JTextField getTxt5() {
        return txt5;
    }

    public JTextField getTxt6() {
        return txt6;
    }

    public JTextField getTxt7() {
        return txt7;
    }

    public JTextField getTxt1() {
        return txt1;
    }

    public JTextField getTxt2() {
        return txt2;
    }

    public InfosPanel(String titre){
        if(Objects.equals(titre, "Informations generales")){
            lbl1 = new JLabel("Solde :");
            add(lbl1);
            txt1=new JTextField(25);
            add(txt1);
            txt1.setEnabled(false);
            txt1.setDisabledTextColor(Color.BLACK);
            txt1.setHorizontalAlignment(SwingConstants.CENTER);
            lbl2 = new JLabel("Derniere operation effectuee :");
            add(lbl2);
            txt2=new JTextField(25);
            add(txt2);
            txt2.setEnabled(false);
            txt2.setDisabledTextColor(Color.BLACK);
            txt2.setHorizontalAlignment(SwingConstants.CENTER);
            lbl3 = new JLabel("Nom complet :");
            add(lbl3);
            txt3 = new JTextField(25);
            add(txt3);
            txt3.setEnabled(false);
            txt3.setDisabledTextColor(Color.BLACK);
            txt3.setHorizontalAlignment(SwingConstants.CENTER);
            lbl4 = new JLabel("CIN :");
            add(lbl4);
            txt4 = new JTextField(25);
            add(txt4);
            txt4.setEnabled(false);
            txt4.setDisabledTextColor(Color.BLACK);
            txt4.setHorizontalAlignment(SwingConstants.CENTER);
            lbl5 = new JLabel("Email :");
            add(lbl5);
            txt5 = new JTextField(25);
            add(txt5);
            txt5.setEnabled(false);
            txt5.setDisabledTextColor(Color.BLACK);
            txt5.setHorizontalAlignment(SwingConstants.CENTER);
            lbl6 = new JLabel("Telephone :");
            add(lbl6);
            txt6 = new JTextField(25);
            add(txt6);
            txt6.setEnabled(false);
            txt6.setDisabledTextColor(Color.BLACK);
            txt6.setHorizontalAlignment(SwingConstants.CENTER);
            lbl7 = new JLabel("Nombre de comptes possedes :");
            add(lbl7);
            txt7 = new JTextField(25);
            add(txt7);
            txt7.setEnabled(false);
            txt7.setDisabledTextColor(Color.BLACK);
            txt7.setHorizontalAlignment(SwingConstants.CENTER);
            lbl1.setForeground(Color.WHITE);
            lbl2.setForeground(Color.WHITE);
            lbl3.setForeground(Color.WHITE);
            lbl4.setForeground(Color.WHITE);
            lbl5.setForeground(Color.WHITE);
            lbl6.setForeground(Color.WHITE);
            lbl7.setForeground(Color.WHITE);
        }else{
            lbl1 = new JLabel("Nombre total des clients :");
            add(lbl1);
            txt1=new JTextField(25);
            add(txt1);
            txt1.setEnabled(false);
            txt1.setDisabledTextColor(Color.BLACK);
            txt1.setHorizontalAlignment(SwingConstants.CENTER);
            lbl2 = new JLabel("Client le plus riche :");
            add(lbl2);
            txt2=new JTextField(25);
            add(txt2);
            txt2.setEnabled(false);
            txt2.setDisabledTextColor(Color.BLACK);
            txt2.setHorizontalAlignment(SwingConstants.CENTER);
            lbl3 = new JLabel("Nombre de clients hommes :");
            add(lbl3);
            txt3 = new JTextField(25);
            add(txt3);
            txt3.setEnabled(false);
            txt3.setDisabledTextColor(Color.BLACK);
            txt3.setHorizontalAlignment(SwingConstants.CENTER);
            lbl4 = new JLabel("Nombre de clients femmes :");
            add(lbl4);
            txt4 = new JTextField(25);
            add(txt4);
            txt4.setEnabled(false);
            txt4.setDisabledTextColor(Color.BLACK);
            txt4.setHorizontalAlignment(SwingConstants.CENTER);
            lbl5 = new JLabel("Nombre total des comptes :");
            add(lbl5);
            txt5 = new JTextField(25);
            add(txt5);
            txt5.setEnabled(false);
            txt5.setDisabledTextColor(Color.BLACK);
            txt5.setHorizontalAlignment(SwingConstants.CENTER);
            lbl6 = new JLabel("Solde maximal :");
            add(lbl6);
            txt6 = new JTextField(25);
            add(txt6);
            txt6.setEnabled(false);
            txt6.setDisabledTextColor(Color.BLACK);
            txt6.setHorizontalAlignment(SwingConstants.CENTER);
            lbl7 = new JLabel("Solde minimal :");
            add(lbl7);
            txt7 = new JTextField(25);
            add(txt7);
            txt7.setEnabled(false);
            txt7.setDisabledTextColor(Color.BLACK);
            txt7.setHorizontalAlignment(SwingConstants.CENTER);
            lbl1.setForeground(Color.WHITE);
            lbl2.setForeground(Color.WHITE);
            lbl3.setForeground(Color.WHITE);
            lbl4.setForeground(Color.WHITE);
            lbl5.setForeground(Color.WHITE);
            lbl6.setForeground(Color.WHITE);
            lbl7.setForeground(Color.WHITE);
        }
        setBackground(new Color(102, 255, 0));
    }
}
