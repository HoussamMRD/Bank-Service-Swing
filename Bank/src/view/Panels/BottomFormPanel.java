package view.Panels;

import javax.swing.*;
import java.awt.*;

public class BottomFormPanel extends JPanel {
    private JButton okButton,noButton;

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getNoButton() {
        return noButton;
    }

    public BottomFormPanel(String a, String b){
        okButton = new JButton(a);
        add(okButton);
        noButton = new JButton(b);
        add(noButton);
        setBackground(new Color(102, 255, 0));
    }

    public BottomFormPanel(String a){
        okButton = new JButton(a);
        add(okButton);
        setBackground(new Color(102, 255, 0));
    }
}
