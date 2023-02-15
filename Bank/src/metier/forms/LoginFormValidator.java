package metier.forms;

import metier.verifiable;
import presentation.model.Admin;
import presentation.model.Banque;
import presentation.model.Client;
import presentation.model.Utilisateur;

import java.util.HashMap;
import java.util.Map;

public class LoginFormValidator implements verifiable{
    private static final String champ_login="Champ login",champ_pass="Champ mot de passe";
    private Map<String,String>errors;
    private String connectionMsg;
    private Banque bank;
    public LoginFormValidator(){
        errors=new HashMap<>();
    }
    public LoginFormValidator(Banque b){
        errors=new HashMap<>();
        this.bank=b;
    }

    public Map<String, String> getErros() {
        return errors;
    }

    public void setErros(String fieldName,String errorMsg) {
        this.errors.put(fieldName,errorMsg);
    }

    public String getConnectionMsg() {
        return connectionMsg;
    }

    public void setConnectionMsg(String connectionMsg) {
        this.connectionMsg = connectionMsg;
    }

    public Banque getBank() {
        return bank;
    }

    public void setBank(Banque bank) {
        this.bank = bank;
    }

    // Fonctions de verification :

    private void verifierLogin(String login) throws FormException {
        if(login!=null && login.trim().length()!=0){
            if(login.trim().length()<4){
                throw new FormException("Login must have more than 4 caracters !");
            }
        }
        else{
            throw new FormException("Login est un champ obligatoire !");
        }
    }
    private void verifierPass(String pass) throws FormException {
        if(pass!=null && pass.trim().length()!=0){
            if(pass.trim().length()<4){
                throw new FormException("Password must have more than 4 caracters !");
            }
        }
        else{
            throw new FormException("Password est un champ obligatoire !");
        }
    }

    public void validerLogin(String login){
        try {
            verifierLogin(login);
        } catch (FormException e) {
            setErros(champ_login,e.getMessage());
        }
    }

    public void validerPass(String pass){
        try {
            verifierPass(pass);
        } catch (FormException e) {
            setErros(champ_pass,e.getMessage());
        }
    }

    public Utilisateur validerSession(String login,String pass){
        Utilisateur session = null;
        validerLogin(login);
        validerPass(pass);
        if(errors.isEmpty()){
            if(isAdmin(login,pass)){
                session= Admin.getInstance();
                setConnectionMsg("Successfully connected as [ADMIN] "+session.getNomComplet());
            }
            else if(isClient(login,pass)!=null){
                session=isClient(login,pass);
                setConnectionMsg("Successfully connected as [CLIENT] "+session.getNomComplet());
//            session= isClient(login,pass,bank);
//            if(session!=null){
//                setConnectionMsg("Successfully conntected as [CLIENT] "+session.getNomComplet());
//            }
            }
            else{
                setConnectionMsg("Connection Failed !");
            }
        }
        return session;
    }
}
