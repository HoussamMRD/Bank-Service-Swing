package view.Panels;

import presentation.model.TypeLog;

import javax.swing.*;
import java.awt.*;

public class CompteOpPanel extends JPanel {
    private JLabel montant;
    private JTextField mtField;

    public JTextField getMtField() {
        return mtField;
    }



    public CompteOpPanel(TypeLog typeLog){
        if(typeLog.equals(TypeLog.RETRAIT))
            montant=new JLabel("Montant a retirer :");
        if (typeLog.equals(TypeLog.VERSEMENT))
            montant=new JLabel("Montant a verser :");
        if (typeLog.equals(TypeLog.VIREMENT))
            montant=new JLabel("Montant du virement :");
        add(montant);
        mtField=new JTextField(15);
        mtField.setHorizontalAlignment(SwingConstants.CENTER);
        add(mtField);
        montant.setForeground(Color.WHITE);
        setBackground(new Color(102, 255, 0));
    }
}
