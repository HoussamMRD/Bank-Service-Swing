package metier;

import dao.daoFiles.ClientDAO;
import dao.daoFiles.FileBasePaths;
import metier.admin.ServiceIHMAdmin;
import metier.clients.ServiceIHMClient;
import presentation.model.Admin;
import presentation.model.Banque;
import presentation.model.Client;

import java.util.Iterator;
import java.util.Objects;

public interface verifiable {
    default boolean isNumeric(String value){
        try {
            Integer.parseInt(value);
            return true;
        } catch(Exception e){
            return false;
        }
    }
    default boolean isDecimal(String value){
        try {
            Double.parseDouble(value);
            return true;
        } catch(Exception e){
            return false;
        }
    }

    default boolean isAdmin(String login,String mdp){
        Admin admin = Admin.getInstance();
        return login.equals(admin.getLogin()) && mdp.equals(admin.getMotDePasse());
    }

    default Client isClient(String login,String mdp){
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.findByLogAndPass(login,mdp);
//        return clientDAO.findAll()
//                .stream()
//                .filter(client -> {return client.getLogin().equals(login) && client.getMotDePasse().equals(mdp);})
//                .findFirst()
//                .orElse(null);
    }

    default Integer login(String log, Banque b) {
        if(log.length()<4) {
            System.out.println("Le nom d'utilisateur doit etre > 4 caracteres");
            return -1;
        }
        if(log.equals(Admin.getInstance().getLogin()))
            return 1;
        else {
            for (Client x : b.getClientsDeBanque()) {
                if (x.getLogin().equals(log))
                    return 0;
            }
            System.out.println("Utilisateur n'existe pas !!");
            return -1;
        }
    }
    default Integer password(String mdp, Banque b) {
        if(mdp.strip().length()==0)
            System.out.println("Le mot de passe est un champ obligatoire !!");
        if(mdp.equals(Admin.getInstance().getMotDePasse()))
            return 1;
        else {
            for (Client x : b.getClientsDeBanque()) {
                if (x.getMotDePasse().equals(mdp))
                    return 0;
            }
            return -1;
        }
    }
    default Integer verifUser(String log, String mdp, String adm, Banque b) {
        switch (adm) {
            case "oui" -> {
                if(log.equals("admin") && mdp.equals("1234")){
                    return 1;
                }
                else return -1;
            }
            case "non" -> {
                for(Client c : b.getClientsDeBanque()){
                    if(Objects.equals(c.getLogin(), log) && Objects.equals(c.getMotDePasse(), mdp)){
                        return 0;
                    }
                }
                return -1;
            }
        }
        return -1;
    }
}
