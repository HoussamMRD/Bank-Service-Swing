package metier.clients;

import dao.daoFiles.CompteDAO;
import dao.daoFiles.FileBasePaths;
import presentation.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class ServiceClient implements iServiceClient{
    private Integer indicC=0;
    private Client c = new Client();
    CompteDAO cmpDAO = new CompteDAO();
    private Banque bank;
    private String nb_Compte;
    public ServiceClient() {}
    public ServiceClient(Banque x) {
        bank=x;
    }

    public ServiceClient(Client c, Banque bank) {
        this.c = c;
        this.bank = bank;
    }

    public Client getC() {
        return c;
    }

    public void setC(Client c) {
        this.c = c;
    }

    public Banque getBank() {
        return bank;
    }

    public void setBank(Banque bank) {
        this.bank = bank;
    }

    public ServiceClient(Client x) {
        c=x;
    }

    @Override
    public boolean versement(Scanner sc) {
        Compte x=null;
        for(Compte xx : cmpDAO.findAll())
            if(xx.getNumeroCompte().equals(nb_Compte))
                x=xx;
        double mt;
        do {
            System.out.print("Montant du versement : ");
            mt=sc.nextDouble();
            if(mt>0){
                x.setSolde(x.getSolde()+mt);
                cmpDAO.update(x);
                x.setLog(TypeLog.VERSEMENT,"Versement de "+mt+" Dhs");
                System.out.print("Voulez-vous un ticket ? [o/n] => ");
                if (sc.next().equals("o")){
                    System.out.println();
                    this.afficherTicket();
                }
                cmpDAO.update(x);
                String clientNBR = "client_ID";
                clientNBR = clientNBR.concat(String.valueOf(c.getId()));
                clientNBR = clientNBR.concat(".txt");
                Path p = FileBasePaths.createClienLogs(clientNBR);
                String logClt = TypeLog.VERSEMENT.toString() + "\t\t" + "Versement de "+
                        String.valueOf(mt)+" DH au compte numero "+nb_Compte+" le "+ LocalDate.now() +"\n";
                try {
                    Files.writeString(p,logClt,StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("\tVersement effectué avec succès.");
                return true;
            }
            else{
                System.out.println("  ** Erreur : le montant doit etre superieur à 0");
            }
        }while(mt<1);
        return false;
    }

    @Override
    public boolean retrait(Scanner sc) {
        Compte x=null;
        for(Compte xx : cmpDAO.findAll())
            if(xx.getNumeroCompte().equals(nb_Compte))
                x=xx;
        double mt;
        do {
            System.out.print("Montant du retrait : ");
            mt=sc.nextDouble();
            if(mt>0 && mt<=x.getSolde()){
                x.setSolde(x.getSolde()-mt);
                cmpDAO.update(x);
                x.setLog(TypeLog.RETRAIT,"Retirer de "+mt+" Dhs");
                System.out.print("Voulez-vous un ticket ? [o/n] => ");
                if (sc.next().equals("o")){
                    System.out.println();
                    this.afficherTicket();
                }
                String clientNBR = "client_ID";
                clientNBR = clientNBR.concat(String.valueOf(c.getId()));
                clientNBR = clientNBR.concat(".txt");

                Path p = FileBasePaths.createClienLogs(clientNBR);
                String logClt = TypeLog.RETRAIT.toString() + "\t\t" + "Retirer de "+
                        String.valueOf(mt)+" DH du compte N°"+nb_Compte+" le "+ LocalDate.now() +"\n";
                try {
                    Files.writeString(p,logClt,StandardOpenOption.APPEND);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("\tRetirer effectué avec succès.");
                return true;
            }
            else{
                System.out.println("  ** Erreur : le montant doit etre superieur à 0 et inférieur ou égal à "+x.getSolde());
            }
        }while(mt<1||mt>x.getSolde());
        return false;
    }

    @Override
    public boolean retrait(int choixRetrait) {
        return false;
    }

    @Override
    public boolean virement(Scanner sc) {
        Compte c1=null;
        Compte x=null;
        for(Compte xx : cmpDAO.findAll())
            if(xx.getNumeroCompte().equals(nb_Compte))
                x=xx;
        String id = "b-co00";
        System.out.print("Numéro du compte recherhé => b-co00");
        id=id.concat(sc.next());
        for (Compte aa : cmpDAO.findAll()) {
            if (aa.getNumeroCompte().equals(id)) {
                c1 = aa;
                break;
            }
        }
        if(c1!=null){
            double mt;
            do {
                System.out.print("Montant du virement : ");
                mt=sc.nextDouble();
                if(mt>0 && mt<=x.getSolde()){
                    x.setSolde(x.getSolde()-mt);
                    cmpDAO.update(x);
                    x.setLog(TypeLog.VIREMENT,"Virement de "+mt+" Dhs");
                    String clientNBR = "client_ID";
                    clientNBR = clientNBR.concat(String.valueOf(c.getId()));
                    clientNBR = clientNBR.concat(".txt");
                    Path p = FileBasePaths.createClienLogs(clientNBR);
                    String logClt = TypeLog.VIREMENT.toString() + "\t\t" + "Virement de "+
                            String.valueOf(mt)+" DH du compte N°"+nb_Compte+" vers le compte N°"+
                            c1.getNumeroCompte()+ " le "+ LocalDate.now() +"\n";
                    try {
                        Files.writeString(p,logClt,StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    c1.setSolde(c1.getSolde()+mt);
                    cmpDAO.update(c1);
                    c1.setLog(TypeLog.VIREMENT,"Virement de "+mt+" Dhs");
                    String clientNBR1 = "client_ID";
                    clientNBR1 = clientNBR1.concat(String.valueOf(c1.getProprietaire().getId()));
                    clientNBR1 = clientNBR1.concat(".txt");
                    Path p1 = FileBasePaths.createClienLogs(clientNBR1);
                    String logClt1 = TypeLog.VIREMENT.toString() + "\t\t" + "Virement de "+
                            String.valueOf(mt)+" DH reçu du compte N°"+x.getNumeroCompte()+
                            " le "+ LocalDate.now() +"\n";
                    try {
                        Files.writeString(p1,logClt1,StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("\tVirement effectué avec succès.");
                    return true;
                }
                else{
                    System.out.println("  ** Erreur : le montant doit etre superieur à 0 et inférieur ou égal à "+x.getSolde());
                }
            }while(mt<1||mt>x.getSolde());
        }
        return false;
    }

    @Override
    public boolean modifierProfile(Integer ch,Scanner sc) {
        if(ch==1){
            System.out.print("Modifier votre email ? [o/n] => ");
            if(sc.next().toLowerCase().equals("o")){
                System.out.print("Donner le nouveau email : ");
                c.setEmail(sc.next());
                System.out.println("\tModification effectué avec succès.");
                return true;
            }
        }
        if(ch==2){
            System.out.print("Modifier votre telephone ? [o/n] => ");
            if(sc.next().toLowerCase().equals("o")){
                System.out.print("Donner le nouveau telephone : ");
                c.setTel(sc.next());
                System.out.println("\tModification effectué avec succès.");
                return true;
            }
        }
        return false;
    }

    @Override
    public void dernieresOperations() {
        if(!c.getComptesClient().get(this.indicC).getLogs().isEmpty()){
            System.out.println(c.getComptesClient().get(this.indicC).toString());
            System.out.println(c.getComptesClient().get(this.indicC).getLogs().toString());
            System.out.println();
        }
        else
            System.out.println("Aucune opération effectuée sur ce compte !!");
//        Compte x;
//        x=c.getComptesClient().get(indicC);
//        if(x.getLogs().size()>0)
//            System.out.println(x.getLogs().get(x.getLogs().size()-1).toString());
//        else
//            System.out.println("Aucune opération effectuée sur ce compte !!");
//        if(indicC==0){
//            x=c.getComptesClient().get(0);
//            if(x.getLogs().size()>0)
//                System.out.println(x.getLogs().get(x.getLogs().size()-1).toString());
////            System.out.println(x.getLogs().toString());
//        }
//        else{
//            x=c.getComptesClient().get(indicC);

//        }
    }

    @Override
    public Double afficherSolde() {
        return c.getComptesClient().get(indicC).getSolde();
    }

    public Integer getIndicC() {
        return indicC;
    }

    public void setIndicC(Integer indicC) {
        this.indicC = indicC;
    }

    @Override
    public Integer choisirCompte(Scanner sc) {
        for(Compte x : c.getComptesClient())
            System.out.println(x.toString());
        String y = "b-co00";
        System.out.print("Numéro du compte à choisir => b-co00");
        y=y.concat(sc.next());
        nb_Compte = y;
        int find=0;
        for(int i=0;i<c.getComptesClient().size();i++){
            if(c.getComptesClient().get(i).getNumeroCompte().equals(y)){
                find++;
            }
        }
        if(find!=0){
            for(int i=0;i<c.getComptesClient().size();i++){
                if(c.getComptesClient().get(i).getNumeroCompte().equals(y)){
                    setIndicC(i);
                }
            }
        }
        return indicC;
    }

    @Override
    public void afficherTicket() {
        Compte x;
        x=c.getComptesClient().get(indicC);
        if(x.getLogs().size()>0)
            System.out.println(x.getLogs().get(x.getLogs().size()-1).toString());
        else
            System.out.println("Aucune opération effectuée sur ce compte !!");
//        if(!c.getComptesClient().get(this.indicC).getLogs().isEmpty()){
//            System.out.println(c.getComptesClient().get(this.indicC).toString());
//            System.out.println(c.getComptesClient().get(this.indicC).getLogs().toString());
//            System.out.println();
//        }
//        else
//            System.out.println("Aucune opération effectuée sur ce compte !!");
//        for (Compte x : c.getComptesClient()) {
//            System.out.println(x.toString());
//            for (Log y : x.getLogs())
//                System.out.println(y.toString());
//        }
    }
}
