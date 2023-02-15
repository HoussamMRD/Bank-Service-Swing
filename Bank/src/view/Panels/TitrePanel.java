package view.Panels;

import javax.swing.*;
import java.awt.*;

public class TitrePanel extends JPanel {
    public TitrePanel(String lbl){
        JLabel titre = new JLabel(lbl);
        titre.setFont(new Font("Serif",Font.BOLD,17));
        add(titre);
        titre.setForeground(Color.WHITE);
        setBackground(new Color(102, 255, 0));
    }
}
