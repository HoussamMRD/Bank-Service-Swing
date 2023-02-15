package view.Panels;

import javax.swing.*;
import java.awt.*;

public class ClientMainPanel extends JPanel {
    private JButton btnMod,btnInfo,btnVersement,btnVirement,btnRetrait,btnChCompte;

    public JButton getBtnMod() {
        return btnMod;
    }

    public JButton getBtnInfo() {
        return btnInfo;
    }

    public JButton getBtnVersement() {
        return btnVersement;
    }

    public JButton getBtnVirement() {
        return btnVirement;
    }

    public JButton getBtnRetrait() {
        return btnRetrait;
    }

    public JButton getBtnChCompte() {
        return btnChCompte;
    }

    public ClientMainPanel(boolean oneAccountOnly){
        btnMod = new JButton("Modifier profil");
        btnMod.setPreferredSize(new Dimension(200,30));
        add(btnMod);
        btnInfo = new JButton("Informations generales");
        btnInfo.setPreferredSize(new Dimension(200,30));
        add(btnInfo);
        btnVersement = new JButton("Versement");
        btnVersement.setPreferredSize(new Dimension(200,30));
        add(btnVersement);
        btnRetrait = new JButton("Retrait");
        btnRetrait.setPreferredSize(new Dimension(200,30));
        add(btnRetrait);
        btnVirement = new JButton("Virement");
        btnVirement.setPreferredSize(new Dimension(200,30));
        add(btnVirement);
        if(!oneAccountOnly){
            btnChCompte = new JButton("Changer de compte");
            btnChCompte.setPreferredSize(new Dimension(200,30));
            add(btnChCompte);
        }
        setBackground(new Color(102, 255, 0));
        setVisible(true);
    }
}
