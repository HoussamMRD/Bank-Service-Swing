package view.Admin;

import dao.daoFiles.CompteDAO;
import presentation.model.Compte;
import view.Panels.BottomFormPanel;
import view.Panels.ComptePanel;
import view.Panels.TableComponents.TablePanel;
import view.Panels.TitrePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCompte extends JFrame {
    CompteDAO compteDAO = new CompteDAO();
    TitrePanel topPnl = new TitrePanel("Modifier compte");
    ComptePanel centerPnl = new ComptePanel(false);
    BottomFormPanel bottomPnl = new BottomFormPanel("Modifier","Annuler");
    private TablePanel testPnl;
    public EditCompte(Compte compte,TablePanel comptesPnl){
        testPnl = comptesPnl;
        this.setTitle("Modifier compte");
        this.setSize(220,360);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(topPnl, BorderLayout.NORTH);
        topPnl.setBorder(BorderFactory.createEmptyBorder(0, 0, 17, 0));

        add(centerPnl,BorderLayout.CENTER);
        centerPnl.getTxt1().setText(compte.getNumeroCompte());
        centerPnl.getTxt2().setText(String.valueOf(compte.getSolde()));
        centerPnl.getTxt3().setText(String.valueOf(compte.getProprietaire().getId()));
        centerPnl.getTxt4().setText(compte.getProprietaire().getNomComplet());
        centerPnl.getTxt5().setText(compte.getDateCreation().toString());
        add(bottomPnl,BorderLayout.SOUTH);
        bottomPnl.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!centerPnl.getTxt2().getText().isEmpty()){
                    double mt = Double.parseDouble(centerPnl.getTxt2().getText());
                    if(mt>0.0){
                        compte.setSolde(mt);
                        compteDAO.update(compte);
                        testPnl.getTableModel().initComptesData(compteDAO.findAll());
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Error : le solde doit etre positif !");
                        centerPnl.getSoldeTxt().setText("");
                    }
                }else
                    JOptionPane.showMessageDialog(null, "Error : le champ solde doit etre rempli !");
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
