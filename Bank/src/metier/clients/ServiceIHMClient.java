package metier.clients;

import metier.authentification.Auth;
import presentation.model.Banque;
import presentation.model.Client;

import java.util.Scanner;

public class ServiceIHMClient implements iServiceIHMClient{
    private ServiceClient scl;
    public ServiceIHMClient() {}
    public ServiceIHMClient(Client x, Banque b) {
        scl = new ServiceClient(x,b);
        Scanner sc = new Scanner(System.in);
        scl.choisirCompte(sc);
    }

    @Override
    public void menuGlobal(Scanner sc) {
        Integer choix;
        do{
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("*                               MENU CLIENT                                 *");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("= 1- [MODIFICATION]                                                         =");
            System.out.println("= 2- [RETRAIT]                                                              =");
            System.out.println("= 3- [INFORMATIONS]                                                         =");
            System.out.println("= 4- [CHANGEMENT DU COMPTE]                                                 =");
            System.out.println("= 5- Se déconnecter                                                         =");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.print("  => Votre choix : ");
            choix=sc.nextInt();
            switch (choix) {
                case 1 -> menuModification(sc);
                case 2 -> menuRetrait(sc);
                case 3 -> menuInformations(sc);
                case 4 -> scl.choisirCompte(sc);
                case 5 -> {
                    Auth a = new Auth(scl.getBank());
                    a.SeDeconnecter();
                }
                default -> System.out.println("Choix non reconnu !!");
            }
        }while (choix!=5);
    }

    @Override
    public void menuModification(Scanner sc) {
        Integer choix;
        do{
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("*                               MENU MODIFICATION                           *");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("= 1- [EMAIL]                                                                =");
            System.out.println("= 2- [TELEPHONE]                                                            =");
            System.out.println("= 3- RETOUR                                                                 =");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.print("  => Votre choix : ");
            choix=sc.nextInt();
            switch (choix){
                case 1:
                    scl.modifierProfile(1,sc);
                    break;
                case 2:
                    scl.modifierProfile(2,sc);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Choix non reconnu !!");
                    break;
            }
        }while (choix!=3);

    }

    @Override
    public void menuRetrait(Scanner sc) {
        Integer choix;
        do{
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("*                               MENU RETRAIT                                *");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("= 1- [VERSEMENT]                                                            =");
            System.out.println("= 2- [RETRAIT]                                                              =");
            System.out.println("= 3- [VIREMENT]                                                             =");
            System.out.println("= 4- RETOUR                                                                 =");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.print("  => Votre choix : ");
            choix=sc.nextInt();
            switch (choix){
                case 1:
                    scl.versement(sc);
                    break;
                case 2:
                    scl.retrait(sc);
                    break;
                case 3:
                    scl.virement(sc);
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Choix non reconnu !!");
                    break;
            }
        }while (choix!=4);
    }

    @Override
    public void menuInformations(Scanner sc) {
        Integer choix;
        do{
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("*                               MENU INFO                                   *");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.println("= 1- [AFFICHER SOLDE]                                                       =");
            System.out.println("= 2- [AFFICHER DERNIERES OPERATIONS]                                        =");
            System.out.println("= 3- [AFFICHER TICKET]                                                      =");
            System.out.println("= 4- RETOUR                                                                 =");
            System.out.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            System.out.print("  => Votre choix : ");
            choix=sc.nextInt();
            switch (choix){
                case 1:
                    System.out.println("Votre solde est : "+scl.afficherSolde());
                    break;
                case 2:
                    scl.dernieresOperations();
                    break;
                case 3:
                    scl.afficherTicket();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Choix non reconnu !!");
                    break;
            }
        }while (choix!=4);
    }
}
