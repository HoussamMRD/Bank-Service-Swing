package metier.forms;

import presentation.model.Banque;
import presentation.model.Client;
import presentation.model.Sexe;
import presentation.model.Utilisateur;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ClientFormValidator {
//    private Banque bank;
    private static final String champ_login="Champ login",champ_pass="Champ mot de passe",
            champ_nom="Champ nom du client",champ_prenom="Champ prenom du client",
            champ_tel="Champ numero de telephone du client",champ_email="Champ email du client",
            champ_cin="Champ CIN du client",champ_sexe="Champ sexe du client";
//    private final LoginFormValidator lfv=new LoginFormValidator(bank);
    private Map<String,String> errorsCl;
    public Map<String, String> getErrorsCl() {
        return errorsCl;
    }

//    public void setErrorsCl(Map<String, String> errorsCl) {
//        this.errorsCl = errorsCl;
//    }
    public void setErrosCl(String fieldName,String errorMsg) {
        this.errorsCl.put(fieldName,errorMsg);
    }
    public String getCreationMsg() {
        return creationMsg;
    }
    public void setCreationMsg(String creationMsg) {
        this.creationMsg = creationMsg;
    }
    private String creationMsg;
    public ClientFormValidator(Banque bank) {
        errorsCl=new HashMap<>();
//        this.bank = bank;
    }
    public ClientFormValidator() {
        errorsCl=new HashMap<>();
    }
    private void verifierLogin(String s) throws FormException {
        if(s!=null && s.trim().length()!=0){
            if(s.trim().length()<4||s.trim().equals("admin")){
                throw new FormException("Client name must have more than 4 caracters !");
            }
        }
        else{
            throw new FormException("Client login est un champ obligatoire !");
        }
    }
    private void validerLogin(String s){
        try {
            verifierLogin(s);
        } catch (FormException e) {
            setErrosCl(champ_login,e.getMessage());
        }
    }
    private void verifierPass(String s) throws FormException {
        if(s!=null && s.trim().length()!=0){
            if(s.trim().length()<4){
                throw new FormException("Client password must have more than 4 caracters !");
            }
        }
        else{
            throw new FormException("Client password est un champ obligatoire !");
        }
    }
    private void validerPass(String s){
        try {
            verifierPass(s);
        } catch (FormException e) {
            setErrosCl(champ_pass,e.getMessage());
        }
    }
    private void verifierNom(String s) throws FormException {
        if(s!=null && s.trim().length()!=0){
            if(s.trim().length()<4){
                throw new FormException("Client name must have more than 4 caracters !");
            }
        }
        else{
            throw new FormException("Client name est un champ obligatoire !");
        }
    }
    private void validerNom(String s){
        try {
            verifierNom(s);
        } catch (FormException e) {
            setErrosCl(champ_nom,e.getMessage());
        }
    }
    private void verifierPrenom(String s) throws FormException {
        if(s!=null && s.trim().length()!=0){
            if(s.trim().length()<4){
                throw new FormException("Client last name must have more than 4 caracters !");
            }
        }
        else{
            throw new FormException("Client last name est un champ obligatoire !");
        }
    }
    private void validerPrenom(String s){
        try {
            verifierPrenom(s);
        } catch (FormException e) {
            setErrosCl(champ_prenom,e.getMessage());
        }
    }
    private void verifierTel(String s) throws FormException {
        if(s!=null && s.trim().length()!=0){
            if(s.trim().length()<10){
                throw new FormException("Client phone number must have 10 caracters !");
            }
        }
        else{
            throw new FormException("Client phone number est un champ obligatoire !");
        }
    }
    private void validerTel(String s){
        try {
            verifierTel(s);
        } catch (FormException e) {
            setErrosCl(champ_tel,e.getMessage());
        }
    }
    private void verifierCIN(String s) throws FormException {
        if(s!=null && s.trim().length()!=0){
            if(s.trim().length()<7){
                throw new FormException("Client CIN must have at least 7 caracters !");
            }
        }
        else{
            throw new FormException("Client CIN est un champ obligatoire !");
        }
    }
    private void validerCIN(String s){
        try {
            verifierCIN(s);
        } catch (FormException e) {
            setErrosCl(champ_cin,e.getMessage());
        }
    }
    private void verifierEmail(String s) throws FormException {
        if(s!=null && s.trim().length()!=0){
            if(!s.trim().matches("[a-zA-Z0-9]{3,}@[a-zA-Z0-9]{3,}\\.[a-zA-Z]{2,}")){
                throw new FormException("Client email must be valid !");
            }
        }
        else{
            throw new FormException("Client email est un champ obligatoire !");
        }
    }
    private void validerEmail(String s){
        try {
            verifierEmail(s);
        } catch (FormException e) {
            setErrosCl(champ_email,e.getMessage());
        }
    }
    private void verifierSexeHomme(String s) throws FormException {
        if(s!=null && s.trim().length()!=0){
            if(!s.trim().equals("Homme")){
                throw new FormException("Client sexe must be female or male !");
            }
        }
        else{
            throw new FormException("Client sexe est un champ obligatoire !");
        }
    }
    private void verifierSexeFemmme(String s) throws FormException {
        if(s!=null && s.trim().length()!=0){
            if(!s.trim().equals("Femme")){
                throw new FormException("Client sexe must be female or male !");
            }
        }
        else{
            throw new FormException("Client sexe est un champ obligatoire !");
        }
    }
    private void validerSexe(String s){
        try {
            if(s.equals("Homme"))
                verifierSexeHomme(s);
            if(s.equals("Femme"))
                verifierSexeFemmme(s);
        } catch (FormException e) {
            setErrosCl(champ_sexe,e.getMessage());
        }
    }
    public Client createSession(String a,String b,String c,String d,String e,String f,String g,String h) {
        Client cl = null;
        validerLogin(a);
        validerPass(b);
        validerNom(c);
        validerPrenom(d);
        validerCIN(e);
        validerEmail(f);
        validerTel(g);
        validerSexe(h);
        if(errorsCl.isEmpty()){
            if(h.equals("Homme")){
                cl=new Client(a,b,c,d,f,e,g,Sexe.HOMME);
                setCreationMsg("Client created successfully !");
            }
            else if(h.equals("Femme")){
                cl=new Client(a,b,c,d,f,e,g,Sexe.FEMME);
                setCreationMsg("Client created successfully !");
            }
        }
        else
            setCreationMsg("Coudln't create client!");
        return cl;
    }

    public Client createSession(String a,String b,String c,String d,String e,String f,String g) {
        Client cl = null;
        validerLogin(a);
        validerPass(b);
        validerNom(c);
        validerPrenom(d);
        validerCIN(e);
        validerEmail(f);
        validerTel(g);
        if(errorsCl.isEmpty()){
            cl=new Client(a,b,c,d,f,e,g,null);
            setCreationMsg("Client created successfully !");
        }
        else
            setCreationMsg("Coudln't create client!");
        return cl;
    }

    public boolean EditClient(String a,String b,String c,String d,String e,String f,String g){
        validerLogin(a);
        validerPass(b);
        validerNom(c);
        validerPrenom(d);
        validerCIN(e);
        validerEmail(f);
        validerTel(g);
//        validerSexe(h);
        return errorsCl.isEmpty();
    }

    public boolean EditClient(String a,String b,String c,String d,String e,String f,String g,String h){
        validerLogin(a);
        validerPass(b);
        validerNom(c);
        validerPrenom(d);
        validerCIN(e);
        validerEmail(f);
        validerTel(g);
        validerSexe(h);
        return errorsCl.isEmpty();
    }
}
