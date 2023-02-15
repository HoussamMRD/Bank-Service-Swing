package metier.admin;

import dao.daoFiles.ClientDAO;
import dao.daoFiles.CompteDAO;
import dao.daoFiles.FileBasePaths;
import metier.forms.ClientFormValidator;
import presentation.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static metier.Colors.*;

public class ServiceAdmin implements iServiceAdmin{
    Banque bank;
    ClientDAO clDao = new ClientDAO();
    CompteDAO cmpDao = new CompteDAO();
    private final ClientFormValidator cf = new ClientFormValidator();
    public ServiceAdmin(){}
    public boolean adminExs(String a,String b){
        Admin ad=Admin.getInstance();
        return ad.getLogin().equals(a) && ad.getMotDePasse().equals(b);
    }
    public Client getCl(Long x){
        for (Client c : bank.getClientsDeBanque()) {
            if (c.getId().equals(x))
                return c;
        }
        return null;
    }
    public boolean userExs(String a,String b){
        for(Client c : bank.getClientsDeBanque()){
            if(c!=null)
                if(c.getLogin().equals(a) && c.getMotDePasse().equals(b))
                    return true;
        }
        return false;
    }
    public ServiceAdmin(Banque B) {
        bank=B;
    }
    @Override
    public Client nouveauClient(Scanner sc) {
        Client cl;
        System.out.println("----------------- Creation client -------------------");
        String a;String b;String c;String d;String e;String f;String g;String h;
        double solde;
        System.out.print("| Login du client : ");
        a=sc.next();
        System.out.print("| Mot de passe du client : ");
        b=sc.next();
        System.out.print("| Nom du client : ");
        c=sc.next();
        System.out.print("| Prenom du client : ");
        d=sc.next();
        System.out.print("| CIN du client : ");
        e=sc.next();
        System.out.print("| Email du client : ");
        f=sc.next();
        System.out.print("| Telephone du client : ");
        g=sc.next();
        System.out.print("| Sexe du client : ");
        h=sc.next();
        cl=cf.createSession(a,b,c,d,e,f,g,h);
        if(cl==null){
            if(cf.getErrorsCl().isEmpty()){
                System.out.println(RED_BOLD_BRIGHT+cf.getCreationMsg());
                System.out.println(WHITE_BRIGHT);
            }
            else{
                cf.getErrorsCl().forEach((field,errMsg)-> System.out.println(RED_BOLD_BRIGHT+errMsg));
                System.out.println(WHITE_BRIGHT);
            }
        }else{
//            System.out.println(CYAN_BOLD_BRIGHT+cf.getCreationMsg());
            System.out.print(WHITE_BRIGHT);
            if(clDao.save(cl)!=null){
                do{
                    System.out.print("| Solde initial du compte de ce client : ");
                    solde = sc.nextDouble();
                    if(solde<0.0)
                        System.out.println("Erreur : le solde doit etre positif !!");
                }while(solde<=0);
                int taille=0;
                String code = "b-co00";
                if(cmpDao.findAll().isEmpty()) {
                    code = code.concat(String.valueOf(1));
                }
                else{
                    taille = cmpDao.findAll().size();
                    taille++;
                    code = code.concat(String.valueOf(taille));
                }
                Compte cp = new Compte(solde,cl);
                cp.setNumeroCompte(code);
                cmpDao.save(cp);
                String clientNBR = "client_ID";
                clientNBR = clientNBR.concat(String.valueOf(cl.getId()));
                clientNBR = clientNBR.concat(".txt");
                Path p = FileBasePaths.createClienLogs(clientNBR);
                String logClt = TypeLog.CREATION.toString() + "\t\t" + "Client cree le "+LocalDate.now()+"\n";
                String compteClt = TypeLog.CREATION.toString() + "\t\t" + "Compte N°"+cp.getNumeroCompte()
                        +" cree le "+LocalDate.now()+"\n";
                try {
                    Files.writeString(p, logClt, StandardOpenOption.APPEND);
                    Files.writeString(p, compteClt, StandardOpenOption.APPEND);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }else{
                System.out.println(RED_BOLD_BRIGHT+"ERREUR : ce client existe deja !!");
                System.out.println(WHITE_BRIGHT);
            }



//            clDao.save(cl);
//            do{
//                System.out.print("| Solde initial du compte de ce client : ");
//                solde = sc.nextDouble();
//                if(solde<0.0)
//                    System.out.println("Erreur : le solde doit etre positif !!");
//            }while(solde<=0);
//            int taille=0;
//            String code = "b-co00";
//            if(cmpDao.findAll().isEmpty()) {
//                code = code.concat(String.valueOf(1));
//            }
//            else{
//                taille = cmpDao.findAll().size();
//                taille++;
//                code = code.concat(String.valueOf(taille));
//            }
//            Compte cp = new Compte(solde,cl);
//            cp.setNumeroCompte(code);
//            cmpDao.save(cp);
//            String clientNBR = "client_ID";
//            clientNBR = clientNBR.concat(String.valueOf(cl.getId()));
//            clientNBR = clientNBR.concat(".txt");
//            Path p = FileBasePaths.createClienLogs(clientNBR);
//            String logClt = TypeLog.CREATION.toString() + "\t\t" + "Client cree le "+LocalDate.now()+"\n";
//            String compteClt = TypeLog.CREATION.toString() + "\t\t" + "Compte N°"+cp.getNumeroCompte()
//                    +" cree le "+LocalDate.now()+"\n";
//            try {
//                Files.writeString(p, logClt, StandardOpenOption.APPEND);
//                Files.writeString(p, compteClt, StandardOpenOption.APPEND);
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
        }
        return cl;
    }

    @Override
    public Client nouveauCompteClientExistant(Scanner sc) {
//        for (Client client : bank.getClientsDeBanque()) {
//            System.out.println(client.toString());
//        }
        Long id;
        Double solde;
        Client c;
        do {
            System.out.println("----------------- Compte client -------------------");
            System.out.print("ID du client : ");
            id = sc.nextLong();//sc.nextLine();
        }while(clDao.findById(id) == null);
        c = clDao.findById(id);
        do{
            System.out.print("Solde du compte : ");
            solde = sc.nextDouble();//sc.nextLine();
            if(solde<0.0)
                System.out.println("Erreur : le solde doit etre positif !!");
        }while(solde<=0);
        int taille=0;
//        List<Compte> lstTest = new ArrayList<>();
        String code = "b-co00";
        if(cmpDao.findAll().isEmpty()) {
            code = code.concat(String.valueOf(1));
        }
        else{
            taille = cmpDao.findAll().size();
            taille++;
            code = code.concat(String.valueOf(taille));
        }
        Compte cp = new Compte(solde,c);
        cp.setNumeroCompte(code);
        cmpDao.save(cp);
        // add to log file of prop
        String clientNBR = "client_ID";
        clientNBR = clientNBR.concat(String.valueOf(c.getId()));
        clientNBR = clientNBR.concat(".txt");
        Path p = FileBasePaths.createClienLogs(clientNBR);
        String compteClt = TypeLog.CREATION.toString() + "\t\t" + "Compte N°"+cp.getNumeroCompte()
                +" cree le "+LocalDate.now()+"\n";
        try {
            Files.writeString(p, compteClt, StandardOpenOption.APPEND);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


//        c.setCompteClient(cp);
//        clDao.update(c);
//        c.setCompteClient(new Compte(solde,c));
        return c;
    }

    @Override
    public Client chercherClientParId(Long id) {
        return getCl(id);
    }

    @Override
    public List<Client> chercherClientParNom(String nom) {
        List<Client> lstC = new ArrayList<Client>();
        for(Client c : bank.getClientsDeBanque()){
            if(c!=null && Objects.equals(c.getNom(), nom))
                lstC.add(c);
        }
        if(lstC.size()>0)
            return lstC;
        else
            return null;
    }

    @Override
    public List<Client> chercherClientParPrenom(String prenom) {
        List<Client> lstC = new ArrayList<Client>();
        for(Client c : bank.getClientsDeBanque()){
            if(c!=null && Objects.equals(c.getPrenom(), prenom))
                lstC.add(c);
        }
        if(lstC.size()>0)
            return lstC;
        else
            return null;
    }

    @Override
    public Client chercherClientParCin(String cin) {
        for(Client c : bank.getClientsDeBanque()){
            if(c!=null && Objects.equals(c.getCin(), cin))
                return c;
        }
        return null;
    }

    @Override
    public Client chercherClientParEmail(String email) {
        for(Client c : bank.getClientsDeBanque()){
            if(c!=null && Objects.equals(c.getEmail(), email))
                return c;
        }
        return null;
    }

    @Override
    public Compte chercherCompteParId(String idCompte) {
        for (Client client : bank.getClientsDeBanque()) {
            for (Compte c : client.getComptesClient()) {
                if (c.getNumeroCompte().equals(idCompte))
                    return c;
            }
        }
        return null;
    }

    @Override
    public List<Compte> chercherCompteParSolde(double solde) {
        List<Compte> lstC = new ArrayList<Compte>();
        for(Client c : bank.getClientsDeBanque()){
            for(Compte x:c.getComptesClient()){
                if(x.getSolde()==solde)
                    lstC.add(x);
            }
        }
        if(lstC.size()>0)
            return lstC;
        else
            return null;
    }

    @Override
    public List<Compte> chercherCompteParDateCreation(LocalDate date) {
        List<Compte> lstC = new ArrayList<Compte>();
        for(Client c : bank.getClientsDeBanque()){
            for(Compte x:c.getComptesClient()){
                if(x.getDateCreation().equals(date))
                    lstC.add(x);
            }
        }
        if(lstC.size()>0)
            return lstC;
        else
            return null;
    }

    @Override
    public List<Compte> chercherCompteParProprietaire(Client proprietaire) {
        List<Compte> lstC = new ArrayList<Compte>();
        for(Client c : bank.getClientsDeBanque()){
            for(Compte x:c.getComptesClient()){
                if(x.getProprietaire()==proprietaire)
                    lstC.add(x);
            }
        }
        if(lstC.size()>0)
            return lstC;
        else
            return null;
    }

    @Override
    public Client modifierClient(String filtre, Scanner sc) {
//        for (Client client : bank.getClientsDeBanque()) {
//            System.out.println(client.toString());
//        }
        Long id;Client c;
        System.out.println("----------------- Modifiation client -------------------");
        System.out.print("ID du client : ");
        id = sc.nextLong();//sc.nextLine();
        c = clDao.findById(id);
        if(c ==null) {
            System.out.println("Client non existant !!");
            return null;
        }
        String x;
        switch (filtre) {
            case "nom" -> {
                System.out.print("Nouveau Nom : ");
                x = sc.next();
                c.setNom(x);
                clDao.update(c);
            }
            case "prenom" -> {
                System.out.print("Nouveau Prenom : ");
                x = sc.next();
                c.setPrenom(x);
            }
            case "email" -> {
                System.out.print("Nouveau Email : ");
                x = sc.next();
                c.setEmail(x);
            }
            case "tel" -> {
                System.out.print("Nouveau numero de telephone : ");
                x = sc.next();
                c.setTel(x);
            }
            case "cin" -> {
                System.out.print("Nouveau Cin : ");
                x = sc.next();
                c.setCin(x);
            }
            case "sexe" -> {
                System.out.print("Nouveau sexe : ");
                x = sc.next();
                if (Objects.equals(x, "homme"))
                    c.setSexe(Sexe.HOMME);
                else if (Objects.equals(x, "femme"))
                    c.setSexe(Sexe.FEMME);
            }
        }
        return c;
    }

    @Override
    public boolean supprimerClient(Long id) {
        if(clDao.findById(id)!=null)
            return clDao.deleteByID(id);
//        if(getCl(id)!=null){
////            bank.getClientsDeBanque().remove(getCl(id));
//            return clDao.deleteByID(id);
//        }
        return false;
    }

    @Override
    public TableauDeBord calculerEtAfficherStatistiques() {
        TableauDeBord res = new TableauDeBord();
        Double solde=0.0,soldemin;
        String nom=null;
        long nbrF=0,nbrH=0,nbrC=0;
        res.setNombreTotaleClient((long)bank.getClientsDeBanque().size());
        for (Client c : bank.getClientsDeBanque()) {
            if(c.getSexe() == Sexe.HOMME)
                nbrH++;
            else
                nbrF++;
            for (Compte x : c.getComptesClient()) {
                nbrC++;
                if (x.getSolde() > solde) {
                    solde = x.getSolde();
                    nom = x.getProprietaire().getNomComplet();
                }

            }
        }
        soldemin = solde;
        for (Client c : bank.getClientsDeBanque()) {
            for (Compte x : c.getComptesClient()) {
                if(x.getSolde()<soldemin)
                    soldemin=x.getSolde();
            }
        }
        res.setMaxSolde(solde);
        res.setMinSolde(soldemin);
        res.setNombreTotaleCompte(nbrC);
        res.setNomClientLePlusRiche(nom);
        res.setTotalClientsFemme(nbrF);
        res.setTotaleClientsHomme(nbrH);
        return res;
    }

    @Override
    public List<Client> trierClientParNom() {
        List<Client> lstC = bank.getClientsDeBanque();
        lstC.sort((c1, c2) -> c1.getNom().compareTo(c2.getNom()));
//        lstC.sort(Comparator.comparing(Client::getNom));
//        bank.setClientsDeBanque(lstC);
        return lstC;
    }

    @Override
    public List<Client> trierClientParCin() {
        List<Client> lstC = bank.getClientsDeBanque();
        lstC.sort((c1, c2) -> c1.getCin().compareTo(c2.getCin()));
//        lstC.sort(Comparator.comparing(Client::getCin));
//        bank.setClientsDeBanque(lstC);
        return lstC;
    }

    @Override
    public List<Client> trierClientParEmail() {
        List<Client> lstC = bank.getClientsDeBanque();
        lstC.sort((c1, c2) -> c1.getEmail().compareTo(c2.getEmail()));
//        lstC.sort(Comparator.comparing(Client::getEmail));
//        bank.setClientsDeBanque(lstC);
        return lstC;
    }

    @Override
    public List<Client> trierClientParSoldeCompte() {
        List<Client> lstC = bank.getClientsDeBanque();
        lstC.sort((c1, c2) -> Double.compare(c1.getSoldeTt(), c2.getSoldeTt()));
//        lstC.sort(Comparator.comparing(Client::getSoldeTt));
//        bank.setClientsDeBanque(lstC);
        return lstC;
    }

    @Override
    public List<Client> trierClientParID() {
        List<Client> lstC = bank.getClientsDeBanque();
        lstC.sort((c1, c2) -> Long.compare(c1.getId(), c2.getId()));
//        lstC.sort(Comparator.comparing(Client::getSoldeTt));
//        bank.setClientsDeBanque(lstC);
        return lstC;
    }

    @Override
    public List<Compte> trierComptesParSolde() {
        List<Compte> lstC = new ArrayList<Compte>();
        for(Client c : bank.getClientsDeBanque())
            lstC.addAll(c.getComptesClient());
        lstC.sort((c1, c2) -> Double.compare(c1.getSolde(), c2.getSolde()));
//        lstC.sort(Comparator.comparing(Compte::getSolde));
        return lstC;
    }

    @Override
    public List<Compte> trierComptesParDateDeCreation() {
        List<Compte> lstC = new ArrayList<Compte>();
        for(Client c : bank.getClientsDeBanque())
            lstC.addAll(c.getComptesClient());
        lstC.sort((c1, c2) -> c1.getDateCreation().compareTo(c2.getDateCreation()));
//        lstC.sort(Comparator.comparing(Compte::getDateCreation));
        return lstC;
    }

    @Override
    public List<Compte> trierComptesParNomProprietaire() {
        List<Compte> lstC = new ArrayList<Compte>();
        for(Client c : bank.getClientsDeBanque())
            lstC.addAll(c.getComptesClient());
        lstC.sort((c1, c2) -> c1.getNomProp().compareTo(c2.getNomProp()));
//        lstC.sort(Comparator.comparing((Compte::getNomProp)));
//        for(int i=0;i<lstC.size();i++){
//            for(int j=0;j<lstC.size();j++){
//                if (lstC.get(i).getProprietaire().getNom().compareTo(lstC.get(j).getProprietaire().getNom()) > 0) {
//                    Compte x = lstC.get(i);
//                    lstC.set(i, lstC.get(j));
//                    lstC.set(j, x);
//                }
//            }
//        }
        return lstC;
    }
}
