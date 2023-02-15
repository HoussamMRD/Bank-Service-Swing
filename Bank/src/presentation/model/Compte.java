package presentation.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Compte {
    private static long compteur = 1;
    private String numeroCompte;
    private static long id=1;
    private Double solde;
    private LocalDate dateCreation;
    private Client proprietaire;
    private List<Log> logs = new ArrayList<>();
    public void setDateCreation(LocalDate dateD) { this.dateCreation = dateD; }
    public void setDateCreation() { this.dateCreation = LocalDate.now(); }
    public void setProprietaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }
    public void setSolde(Double solde) {
        this.solde = solde;
    }
    public void setLog(TypeLog type, String msg) {
        Log log = new Log(LocalDate.now(), LocalTime.now(), type, msg);
        logs.add(log);
    }
    public String getNomProp(){ return getProprietaire().getNom();}
    public Client getProprietaire() {
        return proprietaire;
    }
    public Double getSolde() {
        return solde;
    }
    public String getNumeroCompte() {
        return numeroCompte;
    }
    public void setNBCompte(int x){
        String xx = "b-co00";
        xx = xx.concat(String.valueOf(x));
        this.numeroCompte = xx;
    }

    public void setNumeroCompte(String val) {
        this.numeroCompte = val;
    }
    public void setNumeroCompte() {
        this.numeroCompte = "b-co00" + compteur++;
    }
    public long getId() {
        return id;
    }
//    public void setId(long id) {
//        this.id = id;
//    }
    public LocalDate getDateCreation() {
        return dateCreation;
    }
    public List<Log> getLogs() {
        return logs;
    }

    public Compte(){
//        setNumeroCompte();
        setSolde(0.0);
        setDateCreation();
        setProprietaire(null);
    }
    public Compte(Double solde, Client owner) {
//    	setNumeroCompte();
        setSolde(solde);
        setDateCreation();
        setProprietaire(owner);
    }

    @Override
    public String toString() {
//        String compteStr  = "------------------------------------------------------\n";
        String compteStr = " | N° de Compte : "   + getNumeroCompte()+ " ";
//        compteStr += "| Solde du Compte         : "   + getSolde()    + " Dh\n" ;
//        compteStr += "| Propriétaire du Compte  : "   + getProprietaire().getNomComplet() + "\n" ;
//        compteStr += "| Date de création        : "   + getDateCreation()+"\n";
//        compteStr += "------------------------------------------------------\n";
        return compteStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return Objects.equals(numeroCompte, compte.numeroCompte) && Objects.equals(solde, compte.solde) && Objects.equals(dateCreation, compte.dateCreation) && Objects.equals(proprietaire, compte.proprietaire) && Objects.equals(logs, compte.logs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCompte, solde, dateCreation, proprietaire, logs);
    }
}
