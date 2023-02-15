package view.Client;

import presentation.model.Compte;
import view.Panels.BottomFormPanel;
import view.Panels.TitrePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChoixCompte extends JFrame {
    private JComboBox<String> comptesJComboBox;
    private String compteID;
    public ChoixCompte(List<Compte> lstComptes){
        this.setTitle("Choisir compte");
        this.setSize(250,240);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        TitrePanel topPnl = new TitrePanel("Choisir compte");
        add(topPnl,BorderLayout.NORTH);
        topPnl.setBorder(BorderFactory.createEmptyBorder(0, 0, 17, 0));

        JPanel centerPnl = new JPanel();
        JLabel cpLabel=new JLabel("Selectionnez un compte : ");
        cpLabel.setForeground(Color.WHITE);
        centerPnl.add(cpLabel);
        comptesJComboBox=new JComboBox<>();
        comptesJComboBox.setPreferredSize(new Dimension(155,20));
        for(Compte cc : lstComptes)
            comptesJComboBox.addItem(cc.getNumeroCompte());
        centerPnl.add(comptesJComboBox);
        centerPnl.setBackground(new Color(97, 196, 30));
        add(centerPnl,BorderLayout.CENTER);

        BottomFormPanel bottomPnl = new BottomFormPanel("Valider");
        bottomPnl.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                compteID=(String) comptesJComboBox.getSelectedItem();
                for(Compte cc : lstComptes)
                    if(cc.getNumeroCompte().equals(compteID)){
                        new ClientFrame(cc.getProprietaire(),cc,false);
                        break;
                    }
            }
        });
        add(bottomPnl,BorderLayout.SOUTH);
        setVisible(true);
    }
}
