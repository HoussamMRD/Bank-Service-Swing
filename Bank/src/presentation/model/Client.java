package presentation.model;

import dao.daoFiles.ClientDAO;
import dao.daoFiles.CompteDAO;

import java.util.*;


public class Client extends Utilisateur{
    private String email, cin, tel;
    private Sexe sexe;
    private List<Compte> comptesClient;
    public String getCin() {
        return cin;
    }
    public String getTel() {
        return tel;
    }
    public String getEmail() {
        return email;
    }
    public List<Compte> getComptesClient() {
        return comptesClient;
    }
    public Sexe getSexe() {
        return sexe;
    }
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public void setCin(String cin) {
        this.cin = cin;
    }
    public void setComptesClient(List<Compte> comptesClient) {
        this.comptesClient = comptesClient;
    }
    public void setCompteClient(Compte comptesClient) {
        this.comptesClient.add(comptesClient);
    }
    
    public Client(){
        comptesClient = new ArrayList<>();
    }

    public Client(String login, String pass){
        super(login, pass, "Client");
        comptesClient = new ArrayList<>();
    }

    public Client(String login, String pass, String n, String p){
        super(login, pass, "Client");
        setNom(n);
        setPrenom(p);
        comptesClient = new ArrayList<>();
    }
    public Client(String login, String pass, String n, String p, String mail, String cin, String tel, Sexe sexe){
        super(login, pass, "Client");
        setNom(n);
        setPrenom(p);
        setTel(tel);
        setEmail(mail);
        setCin(cin);
        setSexe(sexe);
        comptesClient = new ArrayList<Compte>();
    }

    public Client(long id, String login, String pass, String n, String p, String cin, String mail, String tel,  Sexe sexe) {
        setId(id);
        setLogin(login);
        setMotDePasse(pass);
        setNom(n);
        setPrenom(p);
        setCin(cin);
        setEmail(mail);
        setTel(tel);
        setSexe(sexe);
    }

//    public Client(Client c){
//        super(c.login, c.motDePasse, "Client");
//        setNom(c.nom);
//        setPrenom(c.prenom);
//        setTel(c.tel);
//        setEmail(c.email);
//        setCin(c.cin);
//        setSexe(c.sexe);
//        comptesClient = new ArrayList<Compte>();
//    }

    public Client(Client client) {
        setId(client.getId());
        setLogin(client.getLogin());
        setMotDePasse(client.getMotDePasse());
        setNom(client.getNom());
        setPrenom(client.getPrenom());
        setCin(client.getCin());
        setEmail(client.getEmail());
        setTel(client.getTel());
        setSexe(client.getSexe());
//        this.comptesClient = new ArrayList<Compte>();
        setComptesClient(client.getComptesClient());
    }

    public double getSoldeTt(){
        double a=0.0;
        for (Compte compte : comptesClient)
            if (compte != null)
                a+= compte.getSolde();
        return a;
    }

    public double getSoldeTotal(Long id){
        double mt = 0.0;
        ClientDAO clientDAO = new ClientDAO();
        CompteDAO compteDAO = new CompteDAO();
        for(Client client : clientDAO.findAll()){
            if(client.getId().equals(this.id)){
                for(Compte compte : compteDAO.findAll()){
                    if(compte.getProprietaire().getId().equals(this.id)){
                        mt+=compte.getSolde();
                    }
                }
            }
        }
        return mt;
    }

    @Override
    public String toString() {

        String      clientStr  = "------------------------------------------------------\n";
                    clientStr += "| Identifiant du Client     : "   + this.id        + "\n";
                    clientStr += "| Nom Complet               : "   + this.getNomComplet() + "\n" ;
                    clientStr += "| Adresse email             : "   + this.email     + "\n" ;
                    clientStr += "| Numéro téléphone          : "   + this.tel       + "\n" ;
                    clientStr += "| Numéro de CIN             : "   + this.cin       + "\n" ;
                    clientStr += "| Sexe                      : "   + this.sexe      + "\n" ;
                    clientStr += "------------------------------------------------------\n";

        return clientStr;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Client c) {
            return this.id.equals(c.getId());
        }
    	return false;
    }


}
