package view.Admin;

import dao.daoFiles.ClientDAO;
import dao.daoFiles.CompteDAO;
import dao.daoFiles.FileBasePaths;
import presentation.model.Compte;
import presentation.model.TypeLog;
import view.Panels.BottomFormPanel;
import view.Panels.ComptePanel;
import view.Panels.TableComponents.TablePanel;
import view.Panels.TitrePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class AddCompte extends JFrame{
    CompteDAO compteDAO = new CompteDAO();
    ClientDAO clientDAO = new ClientDAO();
    TitrePanel topPnl = new TitrePanel("Creer nouveau compte");
    ComptePanel centerPnl = new ComptePanel(true);
    BottomFormPanel bottomPnl = new BottomFormPanel("Creer","Annuler");
    private TablePanel testPnl;
    public AddCompte(TablePanel comptesPnl){
        testPnl = comptesPnl;
        this.setSize(250,280);
        this.setTitle("Ajouter compte");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(topPnl, BorderLayout.NORTH);
        topPnl.setBorder(BorderFactory.createEmptyBorder(0, 0, 17, 0));
        add(centerPnl,BorderLayout.CENTER);
        add(bottomPnl,BorderLayout.SOUTH);
        bottomPnl.getOkButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!centerPnl.getSoldeTxt().getText().isEmpty()){
                    double mt = Double.parseDouble(centerPnl.getSoldeTxt().getText());
                    long idCl = centerPnl.getId();
                    int taille;
                    if(mt>0.0){
                        String code = "b-co00";
                        if(compteDAO.findAll().isEmpty()) {
                            code = code.concat(String.valueOf(1));
                        }
                        else{
                            taille = compteDAO.findAll().size();
                            taille++;
                            code = code.concat(String.valueOf(taille));
                        }
                        Compte cp = new Compte(mt,clientDAO.findById(idCl));
                        cp.setNumeroCompte(code);
                        if(compteDAO.save(cp)!=null){
                            String clientNBR = "client_ID";
                            clientNBR = clientNBR.concat(String.valueOf(idCl));
                            clientNBR = clientNBR.concat(".txt");
                            Path p = FileBasePaths.createClienLogs(clientNBR);
                            String compteClt = TypeLog.CREATION.toString() + "\t\t" + "Compte N°"+cp.getNumeroCompte()
                                    +" cree le "+LocalDate.now()+"\n";
                            try {
                                Files.writeString(p, compteClt, StandardOpenOption.APPEND);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                            testPnl.getTableModel().initComptesData(compteDAO.findAll());
                            dispose();
                        }
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
