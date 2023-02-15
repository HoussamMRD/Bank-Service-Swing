package view.Panels.TableComponents;

import dao.daoFiles.ClientDAO;
import dao.daoFiles.CompteDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TablePanel extends JPanel {

    private JTable table;
    private TableModel tableModel;
    private JScrollPane scrollPane;
    private SearchPanel searchPanel;

    public JTable getTable() {
        return table;
    }

    public SearchPanel getSearchPanel() {
        return searchPanel;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    private void initTableClients(){
        tableModel  = new TableModel();
        tableModel.initColumns("Id", "Nom", "Prénom", "Login", "Pass", "Cin", "Email", "Tel", "Sexe");
        tableModel.initClientsData(new ClientDAO().findAll());
        table       = new JTable(tableModel);
        table.setFont(new Font("Optima", Font.BOLD, 17));
        table.setForeground(new Color(102, 255, 0));
        table.setRowHeight(35);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Optima", Font.BOLD, 20));
        header.setForeground(new Color(44, 74, 171));
        header.setBackground(Color.WHITE);
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
        scrollPane = new JScrollPane(table);
        searchPanel = new SearchPanel(Color.white);
        initActions(true);
    }

    private void initTableComptes(){
        tableModel  = new TableModel();
        tableModel.initColumns("Numero compte","Solde","ID proprietaire","Date de creation");
        tableModel.initComptesData(new CompteDAO().findAll());
        table       = new JTable(tableModel);
        table.setFont(new Font("Optima", Font.BOLD, 17));
        table.setForeground(new Color(102, 255, 0));
        table.setRowHeight(35);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Optima", Font.BOLD, 20));
        header.setForeground(new Color(44, 74, 171));
        header.setBackground(Color.WHITE);
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
        scrollPane = new JScrollPane(table);
        searchPanel = new SearchPanel(Color.white);
        initActions(false);
    }

    private void initActions(boolean isClient){
        if(isClient){
            searchPanel.getCrudPanel().getFilter().addItem("ID");
            searchPanel.getCrudPanel().getFilter().addItem("Nom");
            searchPanel.getCrudPanel().getFilter().addItem("CIN");
            searchPanel.getCrudPanel().getFilter().addItem("Email");
            searchPanel.getCrudPanel().deleteBtn().addActionListener(e->{
                int row = table.getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(this,
                            "Veuillez choisir un client d'abord !!!",
                            "A L E R T",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    long id          =  (long)tableModel.getValueAt(row, 0);
                    String  nom      =  (String)tableModel.getValueAt(row, 1);
                    String  prenom   =  (String)tableModel.getValueAt(row, 2);
                    String nomComplet = nom + " " + prenom;
                    new ClientDAO().deleteById(id);
                    var list = new ClientDAO().findAll();
                    tableModel.initClientsData(list);
                    JOptionPane.showMessageDialog(this,
                            "Le Client "+nomComplet+ " a été supprimé avec succès",
                            "I N F O",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });
            searchPanel.getTxt_search().addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        searchPanel.getBtn_search().doClick();
                    }
                }
            });
            searchPanel.getBtn_search().addActionListener(e->{
                String keyword = searchPanel.getTxt_search().getText();
                var clients =  new ClientDAO().findByKeywordLike(keyword);
                tableModel.initClientsData(clients);
            });
        }
        else{
            searchPanel.getCrudPanel().getFilter().addItem("Numero de compte");
            searchPanel.getCrudPanel().getFilter().addItem("Solde");
            searchPanel.getCrudPanel().getFilter().addItem("ID proprietaire");
            searchPanel.getCrudPanel().getFilter().addItem("Date de creation");
            searchPanel.getCrudPanel().deleteBtn().addActionListener(e->{
                int row = table.getSelectedRow();
                if(row == -1){
                    JOptionPane.showMessageDialog(this,
                            "Veuillez choisir un compte d'abord !!!",
                            "A L E R T",
                            JOptionPane.ERROR_MESSAGE);
                }
                else{
                    String id = (String) tableModel.getValueAt(row, 0);
                    new CompteDAO().deleteByNbCompte(id);
                    var list = new CompteDAO().findAll();
                    tableModel.initComptesData(list);
                    JOptionPane.showMessageDialog(this,
                            "Le compte N"+id+ " a été supprimé avec succès",
                            "I N F O",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            });
            searchPanel.getTxt_search().addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        searchPanel.getBtn_search().doClick();
                    }
                }
            });
            searchPanel.getBtn_search().addActionListener(e->{
                String keyword = searchPanel.getTxt_search().getText();
                var comptes =  new CompteDAO().findByKeywordLike(keyword);
                tableModel.initComptesData(comptes);
            });
        }
    }
    public TablePanel(boolean isClient){
        if(isClient){
            initTableClients();
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            add(searchPanel, BorderLayout.SOUTH);
        }
        else{
            initTableComptes();
            setLayout(new BorderLayout());
            add(scrollPane, BorderLayout.CENTER);
            add(searchPanel, BorderLayout.SOUTH);
        }
    }
}
