package presentation.model;

import java.util.*;

public class Banque {

    private static long          compteur = 1;
    private Long                 idBanque;
    private String              nomBanque;
    private String              adresseBanque;
    private String              telBanque;
    private String              emailBanque;
    private List<Client>        clientsDeBanque = new ArrayList<>();
    public Banque()
                    {
                        idBanque = compteur++;
                        insererDonneesTest();
                    }
    public Banque(String nom, String adresse, String tel, String mail)
                    {
                        idBanque = compteur++;
                        nomBanque = nom;
                        telBanque = tel;
                        adresseBanque = adresse;
                        emailBanque = mail;
                        insererDonneesTest();
                    }

    public Long getIdBanque() {
        return idBanque;
    }
    public String getNomBanque() {
        return nomBanque;
    }
    public String getEmailBanque() {
        return emailBanque;
    }
    public String getTelBanque() {
        return telBanque;
    }
    public String getAdresseBanque() {
        return adresseBanque;
    }
    public List<Client> getClientsDeBanque() {
        return clientsDeBanque;
    }

    public void setIdBanque(Long idBanque) {
        this.idBanque = idBanque;
    }
    public void setNomBanque(String nomBanque) {
        this.nomBanque = nomBanque;
    }
    public void setEmailBanque(String emailBanque) {
        this.emailBanque = emailBanque;
    }
    public void setAdresseBanque(String adresseBanque) {
        this.adresseBanque = adresseBanque;
    }
    public void setTelBanque(String telBanque) {
        this.telBanque = telBanque;
    }
    public void setClientsDeBanque(List<Client> clientsDeBanque) {
        this.clientsDeBanque = clientsDeBanque;
    }
    public void setClientDeBanque(Client client) {
    	if(!ClientUnique(client))
            this.clientsDeBanque.add(client);
    }

    public Compte getCompte(String nb){
        for (Client client : clientsDeBanque) {
            for (int j = 0; j < client.getComptesClient().size(); j++) {
                if (Objects.equals(client.getComptesClient().get(j).getNumeroCompte(), nb))
                    return client.getComptesClient().get(j);
            }
        }
        return null;
    }

    public boolean dropCL(String l,String m,String c,String e){
        for(Client x : getClientsDeBanque())
            if(x.getLogin().equals(l) && x.getMotDePasse().equals(m) && x.getCin().equals(c) && x.getEmail().equals(e)){
                getClientsDeBanque().remove(x);
                return true;
            }
        return false;
    }

    public boolean ClientUnique(Client c){
        for(Client x : getClientsDeBanque())
            if(x.login.equals(c.login) && x.motDePasse.equals(c.motDePasse) && x.getCin().equals(c.getCin()) && x.getEmail().equals(c.getEmail()))
                return true;
        return false;
    }

    public void insererDonneesTest(){
        Client c= new Client("test","1234","Chlouchi","Mohammad","test@gmail.com","G345999","0620209815",Sexe.HOMME);
        Client c1= new Client("test1","12345","Aouzen","Mourad","mest1@gmail.com","L345454","0698766545",Sexe.FEMME);
        Client c2= new Client("test2","123455","TEST","TEST","sest2@gmail.com","SM400576","0734563266",Sexe.HOMME);
        Client c3= new Client("test3","123456","BenAhmadi","Youssra","aest3@gmail.com","ML405576","0623235674",Sexe.FEMME);
//        Compte c01= new Compte(20000.0,c);
//        Compte c02= new Compte(3000.0,c);
//        Compte c03= new Compte(2500.0,c1);
//        Compte c04= new Compte(4000.0,c2);
//        Compte c05= new Compte(5000.0,c3);
//        c.getComptesClient().add(c01);
//        c.getComptesClient().add(c02);
//        c1.getComptesClient().add(c03);
//        c2.getComptesClient().add(c04);
//        c3.getComptesClient().add(c05);
        this.getClientsDeBanque().add(c);
        this.getClientsDeBanque().add(c1);
        this.getClientsDeBanque().add(c2);
        this.getClientsDeBanque().add(c3);
    }
}
