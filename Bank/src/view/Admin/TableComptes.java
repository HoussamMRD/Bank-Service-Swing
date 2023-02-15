package view.Admin;

import dao.daoFiles.CompteDAO;
import view.Panels.TableComponents.TablePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableComptes extends JFrame {
    CompteDAO compteDAO = new CompteDAO();
    TablePanel tblPnl;
    public JFrame getTableComptes(){return this;}
    public TableComptes(){
        setTitle("Liste des comptes");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tblPnl = new TablePanel(false);
        add(tblPnl);
        tblPnl.getSearchPanel().getCrudPanel().getBtn_add().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCompte(tblPnl);
            }
        });
        tblPnl.getSearchPanel().getCrudPanel().getFilter().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tblPnl.getSearchPanel().getCrudPanel().getFilter().getSelectedItem()=="Numero de compte"){
                    tblPnl.getTableModel().initComptesData(compteDAO.findAll());
                }
                if(tblPnl.getSearchPanel().getCrudPanel().getFilter().getSelectedItem()=="Solde"){
                    tblPnl.getTableModel().initComptesData(compteDAO.trierPar("Solde"));
                }
                if(tblPnl.getSearchPanel().getCrudPanel().getFilter().getSelectedItem()=="Date de creation"){
                    tblPnl.getTableModel().initComptesData(compteDAO.trierPar("Date de creation"));
                }
                if(tblPnl.getSearchPanel().getCrudPanel().getFilter().getSelectedItem()=="ID proprietaire"){
                    tblPnl.getTableModel().initComptesData(compteDAO.trierPar("ID proprietaire"));
                }
            }
        });
        tblPnl.getSearchPanel().getCrudPanel().getBtn_edit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tblPnl.getTable().getSelectedRow();
                if(row == -1)
                    JOptionPane.showMessageDialog(null, "Veuillez choisir un compte d'abord !!!", "A L E R T", JOptionPane.ERROR_MESSAGE);
                else{
                    String nbCompte = (String)tblPnl.getTableModel().getValueAt(row, 0);
                    new EditCompte(compteDAO.findByNbCompte(nbCompte),tblPnl);
                }
            }
        });
        setVisible(true);
    }
}
