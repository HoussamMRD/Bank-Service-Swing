package view.Admin;

import dao.daoFiles.ClientDAO;
import view.Panels.TableComponents.TablePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableClients extends JFrame {
    ClientDAO clientDAO = new ClientDAO();
    TablePanel tblPnl;
    public JFrame tableClientFrame(){return this;}
    public TableClients(){
        setTitle("Liste des clients");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        tblPnl = new TablePanel(true);
        add(tblPnl);
        tblPnl.getSearchPanel().getCrudPanel().getBtn_add().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddClient(tblPnl);
            }
        });
        tblPnl.getSearchPanel().getCrudPanel().getBtn_edit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tblPnl.getTable().getSelectedRow();
                if(row == -1)
                    JOptionPane.showMessageDialog(null, "Veuillez choisir un client d'abord !!!", "A L E R T", JOptionPane.ERROR_MESSAGE);
                else{
                    long id = (long)tblPnl.getTableModel().getValueAt(row, 0);
                    new EditClient(clientDAO.findById(id),tblPnl);
                }
            }
        });
        tblPnl.getSearchPanel().getCrudPanel().getFilter().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tblPnl.getSearchPanel().getCrudPanel().getFilter().getSelectedItem()=="ID"){
                    tblPnl.getTableModel().initClientsData(clientDAO.findAll());
                }
                if(tblPnl.getSearchPanel().getCrudPanel().getFilter().getSelectedItem()=="Nom"){
                    tblPnl.getTableModel().initClientsData(clientDAO.trierPar("Nom"));
                }
                if(tblPnl.getSearchPanel().getCrudPanel().getFilter().getSelectedItem()=="CIN"){
                    tblPnl.getTableModel().initClientsData(clientDAO.trierPar("CIN"));
                }
                if(tblPnl.getSearchPanel().getCrudPanel().getFilter().getSelectedItem()=="Email"){
                    tblPnl.getTableModel().initClientsData(clientDAO.trierPar("Email"));
                }
            }
        });
        setVisible(true);
    }
}
