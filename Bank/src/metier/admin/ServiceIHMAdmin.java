package metier.admin;

import metier.authentification.Auth;
import presentation.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class ServiceIHMAdmin implements iServiceIHMAdmin{
    private ServiceAdmin servad;
    public ServiceIHMAdmin() {}
    public ServiceIHMAdmin(Banque x) {
        servad = new ServiceAdmin(x);
    }
    public void menuCRUD(Scanner sc) {
        Integer choix;
        do{
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("*                               MENU CRUD                                   *");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("= 1- [AJOUTER]                                                              =");
            System.out.println("= 2- [CONSULTER]                                                            =");
            System.out.println("= 3- [MODIFIER]                                                             =");
            System.out.println("= 4- [SUPPRIMER]                                                            =");
            System.out.println("= 5- RETOUR                                                                 =");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.print("  => Votre choix : ");
            choix=sc.nextInt();
            switch (choix){
                case 1:
                    menuAjout(sc);
                    break;
                case 2:
                    menuInformations(sc);
                    break;
                case 3:
                    menuModification(sc);
                    break;
                case 4:
                    menuSuppression(sc);
                    break;
                case 5:
                    break;
                default :
                    System.out.println("Choix non reconnu !!");
                    break;
            }
        }while(choix!=5);
    }
    public void menuGlobal(Scanner sc) {
        Integer choix,choix2;
        System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
        System.out.println("*                  BIENVENUE CHER(S) ADMIN                                  *");
        System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=\n\n");
        System.out.println("Session ouverte pour [ Admin : "+ Admin.getInstance().getNomComplet() +"]\n");
        do {
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("*                               MENU ADMIN                                  *");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("= 1- [Service CRUD]                                                         =");
            System.out.println("= 2- [Service informatique]                                                 =");
            System.out.println("= 3- [Service Trie]                                                         =");
            System.out.println("= 4- [Tableau de bord - Statistique]                                        =");
            System.out.println("= 5- Se déconnecter                                                         =");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.print("  => Votre choix : ");
            choix = sc.nextInt();sc.nextLine();
            switch(choix) {
                case 1:
                    menuCRUD(sc);
                    break;
                case 2:
                    menuRecherche(sc);
                    break;
                case 3:
                    menuTrie(sc);
                    break;
                case 4:
                    tableauDeBord(sc);
                    break;
                case 5:
                    Auth a =new Auth(servad.bank);
                    a.SeDeconnecter();
                    break;
                default:
                    System.out.println("Choix non reconnu !!");
                    break;
            }
        }while(choix!=5);
    }
    @Override
    public void menuModification(Scanner sc) {
        Integer choix;
        do {
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("*                            MENU MODIFICATION                              *");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("= 1- [MODOFIER NOM]                                                         =");
            System.out.println("= 2- [MODIFIER PRENOM]                                                      =");
            System.out.println("= 3- [MODIFIER EMAIL]                                                       =");
            System.out.println("= 4- [MODIFIER NUMERO DE TELEPHONE]                                         =");
            System.out.println("= 5- [MODIFIER CIN]                                                         =");
            System.out.println("= 6- [MODIFIER SEXE]                                                        =");
            System.out.println("= 7- RETOUR                                                                 =");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.print("  => Votre choix : ");
            choix = sc.nextInt();sc.nextLine();
            switch(choix) {
                case 1:
                    if(servad.bank.getClientsDeBanque().size()>0)
                        servad.modifierClient("nom", sc);
                    break;
                case 2:
                    if(servad.bank.getClientsDeBanque().size()>0)
                        servad.modifierClient("prenom", sc);
                    break;
                case 3:
                    if(servad.bank.getClientsDeBanque().size()>0)
                        servad.modifierClient("email", sc);
                    break;
                case 4:
                    if(servad.bank.getClientsDeBanque().size()>0)
                        servad.modifierClient("tel", sc);
                    break;
                case 5:
                    if(servad.bank.getClientsDeBanque().size()>0)
                        servad.modifierClient("cin", sc);
                    break;
                case 6:
                    if(servad.bank.getClientsDeBanque().size()>0)
                        servad.modifierClient("sexe", sc);
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Choix non reconnu !!");
                    break;
            }
        }while(choix!=7);
    }

    @Override
    public void menuRecherche(Scanner sc) {
        Integer choix;
        String res;
        do {
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("*                               MENU RECHERCHE                              *");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("= 1- [CHERCHER CLIENT PAR ID]                                               =");
            System.out.println("= 2- [CHERCHER CLIENT PAR NOM]                                              =");
            System.out.println("= 3- [CHERCHER CLIENT PAR PRENOM]                                           =");
            System.out.println("= 4- [CHERCHER CLIENT PAR CIN]                                              =");
            System.out.println("= 5- [CHERCHER CLIENT PAR EMAIL]                                            =");
            System.out.println("= 6- [CHERCHER COMPTE PAR ID]                                               =");
            System.out.println("= 7- [CHERCHER COMPTE PAR SOLDE]                                            =");
            System.out.println("= 8- [CHERCHER COMPTE PAR DATE DE CREATION]                                 =");
            System.out.println("= 9- RETOUR                                                                 =");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.print("  => Votre choix : ");
            choix = sc.nextInt();
            sc.nextLine();
            switch(choix) {
                case 1:
                    if(servad.bank.getClientsDeBanque().size()>0){
                        System.out.print("Id client : ");
                        Long ss = sc.nextLong();sc.nextLine();
                        System.out.println(servad.chercherClientParId(ss));
                    }
                    break;
                case 2:
                    if(servad.bank.getClientsDeBanque().size()>0){
                        System.out.print("Nom client : ");
                        res = sc.nextLine();
                        System.out.println(servad.chercherClientParNom(res));
                    }
                    break;
                case 3:
                    if(servad.bank.getClientsDeBanque().size()>0){
                        System.out.print("Prenom client : ");
                        res = sc.nextLine();
                        System.out.println(servad.chercherClientParPrenom(res));
                    }
                    break;
                case 4:
                    if(servad.bank.getClientsDeBanque().size()>0){
                        System.out.print("CIN client : ");
                        res = sc.nextLine();
                        System.out.println(servad.chercherClientParCin(res));
                    }
                    break;
                case 5:
                    if(servad.bank.getClientsDeBanque().size()>0){
                        System.out.print("Email client : ");
                        res = sc.nextLine();
                        System.out.println(servad.chercherClientParEmail(res));
                    }
                    break;
                case 6:
                    if(servad.bank.getClientsDeBanque().size()>0){
                        System.out.print("Id compte : ");
                        res = sc.nextLine();
                        System.out.println(servad.chercherCompteParId(res));
                    }
                    break;
                case 7:
                    if(servad.bank.getClientsDeBanque().size()>0){
                        System.out.print("Solde compte : ");
                        Double s = sc.nextDouble();sc.nextLine();
                        for (Compte compte : servad.chercherCompteParSolde(s)) {
                            System.out.println(compte.toString());
                        }
                    }
                    break;
                case 8:
                    if(servad.bank.getClientsDeBanque().size()>0){
                        System.out.print("Date de creation compte (yyyy-MM-dd) : ");
                        res = sc.next().trim();
                        LocalDate dateTime = LocalDate.parse(res);
                        System.out.println(servad.chercherCompteParDateCreation(dateTime));
                    }
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Choix non reconnue !!");
                    break;
            }
        }while(choix!=9);
    }

    @Override
    public void menuInformations(Scanner sc) {
        Integer choix;
        do {
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("*                              MENU INFORMATIONS                            *");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("= 1- [CONSULTER TOUS LES CLIENTS]                                           =");
            System.out.println("= 2- [CONSULTER TOUS LES COMPTES]                                           =");
            System.out.println("= 3- [CONSULTER UN CLIENT]                                                  =");
            System.out.println("= 4- [CONSULTER UN COMPTE]                                                  =");
            System.out.println("= 5- RETOUR                                                                 =");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.print("  => Votre choix : ");
            choix = sc.nextInt();sc.nextLine();
            switch(choix) {
                case 1:
                    for (Client c : servad.bank.getClientsDeBanque()) {
                        System.out.println(c.toString());
                    }
                    break;
                case 2:
                    for (Client c : servad.bank.getClientsDeBanque()) {
                        for (Compte x : c.getComptesClient()) {
                            System.out.println(x.toString());
                        }
                    }
                    break;
                case 3:
                    if(servad.bank.getClientsDeBanque().size()>0){
                        System.out.println("Id du client recherché : ");
                        Long s = sc.nextLong();sc.nextLine();
                        if(servad.getCl(s)!=null)
                            System.out.println(servad.getCl(s).toString());
                        else
                            System.out.println("Client introuvable !!");
                    }
                    break;
                case 4:
                        System.out.println("Numéro du compte recherché : ");
                        String a = sc.nextLine();
                        int t=0;
                        for (Client c : servad.bank.getClientsDeBanque()) {
                            for (Compte x : c.getComptesClient()) {
                                if(Objects.equals(x.getNumeroCompte(), a)){
                                    System.out.println(x.toString());t=1;}
                            }
                        }
                        if(t==0)
                            System.out.println("Compte introuvable !!");
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Choix non reconnu !!");
                    break;
            }
        }while(choix!=5);
    }

    @Override
    public void menuAjout(Scanner sc) {
        Integer choix;
        do {
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("*                                MENU CREATION                              *");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("= 1- [CREATION D'UN CLIENT]                                                 =");
            System.out.println("= 2- [CREATION D'UN COMPTE]                                                 =");
            System.out.println("= 3- RETOUR                                                                 =");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.print("  => Votre choix : ");
            choix = sc.nextInt();sc.nextLine();
            switch(choix) {
                case 1:
                    servad.nouveauClient(sc);
//                    if(servad.nouveauClient(sc)!=null)
//                        System.out.println("Client créé ");
//                    else
//                        System.out.println("Impossible de créer le client");
                    break;
                case 2:
                    if(servad.nouveauCompteClientExistant(sc)!=null)
                        System.out.println("Compte créé ");
                    else
                        System.out.println("Impossible de créer le compte");
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Choix non reconnu !!");
                    break;
            }
        }while(choix!=3);
    }

    @Override
    public void menuSuppression(Scanner sc) {
        if(servad.bank.getClientsDeBanque().size()>00){
        System.out.println("Id du client à supprimer : ");
        Long s = sc.nextLong();sc.nextLine();
        if(servad.supprimerClient(s))
            System.out.println("Client supprimé avec succès !");
        else
            System.out.println("Impossible de supprimer ce client !");
        }
    }

    @Override
    public void tableauDeBord(Scanner sc) {
        Integer choix;
        TableauDeBord tb = servad.calculerEtAfficherStatistiques();
        do {
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("*                              MENU INFORMATIONS                            *");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("= 1- [NOMBRE TOTALE DES CLIENTS]                                            =");
            System.out.println("= 2- [NOMBRE TOTALE DES COMPTES]                                            =");
            System.out.println("= 3- [SOLDE MAXIMUM]                                                        =");
            System.out.println("= 4- [SOLDE MINIMUM]                                                        =");
            System.out.println("= 5- [NOM DU CLIENT LE PLUS RICHE]                                          =");
            System.out.println("= 6- [NOMBRE DES CLIENTS HOMME]                                             =");
            System.out.println("= 7- [NOMBRE DES CLIENTS FEMME]                                             =");
            System.out.println("= 8- RETOUR                                                                 =");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.print("  => Votre choix : ");
            choix = sc.nextInt();sc.nextLine();
            switch(choix) {
                case 1:
                    System.out.println("Nombre total des clients : "+tb.getNombreTotaleClient());
                    break;
                case 2:
                    System.out.println("Nombre total des comptes : "+tb.getNombreTotaleCompte());
                    break;
                case 3:
                    System.out.println("Solde max : "+tb.getMaxSolde());
                    break;
                case 4:
                    System.out.println("Solde min : "+tb.getMinSolde());
                    break;
                case 5:
                    System.out.println("Nom du client le plus riche : "+tb.getNomClientLePlusRiche());
                    break;
                case 6:
                    System.out.println("Nombre des clients hommes : "+tb.getTotaleClientsHomme());
                    break;
                case 7:
                    System.out.println("Nombre des clients femmes : "+tb.getTotalClientsFemme());
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Choix non reconnu !!");
                    break;
            }
        }while(choix!=8);
    }

    @Override
    public void menuTrie(Scanner sc) {
        Integer choix;
        do{
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("*                               MENU TRIE                                   *");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("= 1- [TRIER CLIENT PAR NOM]                                                 =");
            System.out.println("= 2- [TRIER CLIENT PAR CIN]                                                 =");
            System.out.println("= 3- [TRIER CLIENT PAR EMAIL]                                               =");
            System.out.println("= 4- [TRIER CLIENT PAR SOLDE]                                               =");
            System.out.println("= 5- [TRIER COMPTE PAR SOLDE]                                               =");
            System.out.println("= 6- [TRIER COMPTE PAR DATE DE CREATION]                                    =");
            System.out.println("= 7- [TRIER COMPTE PAR NOM DU PROPRIETAIRE]                                 =");
            System.out.println("= 8- RETOUR                                                                 =");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.print("  => Votre choix : ");
            choix = sc.nextInt();sc.nextLine();
            switch (choix) {
                case 1 -> {
                    System.out.println(servad.trierClientParNom().toString());
//                    for (Client c : servad.bank.getClientsDeBanque())
//                        System.out.println(c.toString());
                }
                case 2 -> {
                    System.out.println(servad.trierClientParCin().toString());
//                    for (Client c : servad.bank.getClientsDeBanque())
//                        System.out.println(c.toString());
                }
                case 3 -> {
                    servad.trierClientParEmail();
//                    for (Client c : servad.bank.getClientsDeBanque())
//                        System.out.println(c.toString());
                }
                case 4 -> {
                    System.out.println(servad.trierClientParSoldeCompte().toString());
//                    for (Client c : servad.bank.getClientsDeBanque())
//                        System.out.println(c.toString());
                }
                case 5 -> {
                    System.out.println(servad.trierComptesParSolde().toString());
//                    for (Client c : servad.bank.getClientsDeBanque())
//                        for(Compte x : c.getComptesClient())
//                            System.out.println(x.toString());
                }
                case 6 -> {
                    System.out.println(servad.trierComptesParDateDeCreation().toString());
//                    for (Client c : servad.bank.getClientsDeBanque())
//                        for(Compte x : c.getComptesClient())
//                            System.out.println(x.toString());
                }
                case 7 -> {
                    System.out.println(servad.trierComptesParNomProprietaire().toString());
//                    for (Client c : servad.bank.getClientsDeBanque())
//                        for(Compte x : c.getComptesClient())
//                            System.out.println(x.toString());
                }
                default -> System.out.println("Choix non reconnue !!");
            }
        }while(choix!=8);
    }

    @Override
    public void menuComptabilite(Scanner sc) {

    }
}
