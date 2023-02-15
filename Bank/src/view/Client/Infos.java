package view.Client;

import dao.daoFiles.ClientDAO;
import dao.daoFiles.CompteDAO;
import presentation.model.Client;
import presentation.model.Compte;
import view.Panels.BottomFormPanel;
import view.Panels.InfosPanel;
import view.Panels.TitrePanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Infos extends JFrame {
    ClientDAO clientDAO = new ClientDAO();
    CompteDAO compteDAO = new CompteDAO();
    TitrePanel topPnl = new TitrePanel("Informations generales");
    InfosPanel centerPnl = new InfosPanel("Informations generales");
    BottomFormPanel bottomPnl = new BottomFormPanel("OK");
    public Infos(Compte compte){
        this.setSize(260,450);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        add(topPnl, BorderLayout.NORTH);
        topPnl.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        centerPnl.getTxt1().setText(compte.getSolde().toString()+" DH");
        centerPnl.getTxt2().setText(getLastLog(compte.getProprietaire()));
        centerPnl.getTxt3().setText(compte.getProprietaire().getNomComplet());
        centerPnl.getTxt4().setText(compte.getProprietaire().getCin());
        centerPnl.getTxt5().setText(compte.getProprietaire().getEmail());
        centerPnl.getTxt6().setText(compte.getProprietaire().getTel());
        if(compteDAO.findByIdProp(compte.getProprietaire().getId()).size()>0)
            centerPnl.getTxt7().setText(String.valueOf(compteDAO.findByIdProp(compte.getProprietaire().getId()).size()));
        else
            centerPnl.getTxt7().setText("0");
        add(centerPnl,BorderLayout.CENTER);
        add(bottomPnl,BorderLayout.SOUTH);
        setResizable(false);
        setVisible(true);
    }

    private String getLastLog(Client client){
        String Log=clientDAO.getLogs(client).get(clientDAO.getLogs(client).size()-1);
        double mt=0.0;
        LocalDate dateLog=null;
        Pattern doublePattern = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");
        Matcher doubleMatcher = doublePattern.matcher(Log);
        if (doubleMatcher.find()) {
            mt = Double.parseDouble(doubleMatcher.group());
        }
        Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
        Matcher dateMatcher = datePattern.matcher(Log);
        if (dateMatcher.find()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateLog = LocalDate.parse(dateMatcher.group(), formatter);
        }
        if(Log.contains("VIREMENT"))
            return "Virement de "+mt+" DH le "+dateLog;
        if(Log.contains("VERSEMENT"))
            return "Versement de "+mt+" DH le "+dateLog;
        if(Log.contains("RETRAIT"))
            return "Retrait de "+mt+" DH le "+dateLog;
        if(Log.contains("CREATION"))
            return Log;
        return null;
    }
}
