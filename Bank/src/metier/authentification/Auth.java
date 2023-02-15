package metier.authentification;

import dao.daoFiles.ClientDAO;
import metier.admin.ServiceIHMAdmin;
import metier.clients.ServiceClient;
import metier.clients.ServiceIHMClient;
import metier.clients.iServiceIHMClient;
import metier.forms.FormException;
import metier.forms.LoginFormValidator;
import metier.verifiable;
import presentation.model.Admin;
import presentation.model.Banque;
import presentation.model.Client;
import presentation.model.Utilisateur;

import java.util.Scanner;

import static metier.Colors.*;

public class Auth implements verifiable,iAuth {
    private iServiceIHM sihm;
    private Banque bank;
    private final LoginFormValidator lfv=new LoginFormValidator();
    private Utilisateur sess=null;

    public Auth() {}
    public Auth(Banque b) {
        bank = b;
        lfv.setBank(b);
    }

    @Override
    public void seConnecter(Scanner sc) {
        String log,mdp;
        do {
            System.out.println("======================================================================");
            System.out.println("|==========                   MENU LOGIN                   ==========|");
            System.out.println("======================================================================");
            System.out.print("| Login : ");
            log=sc.next();
            System.out.print("| Mot de passe : ");
            mdp=sc.next();
            sess=lfv.validerSession(log,mdp);
            if(sess==null){
                if(lfv.getErros().isEmpty()){
                    System.out.println(RED_BOLD_BRIGHT+lfv.getConnectionMsg());
                    System.out.println(WHITE_BRIGHT);
                }
                else{
                    lfv.getErros().forEach((field,errMsg)-> System.out.println(RED_BOLD_BRIGHT+errMsg));
                    System.out.println(WHITE_BRIGHT);
                }
            }else{
                System.out.println(CYAN_BOLD_BRIGHT+lfv.getConnectionMsg());
                System.out.println(WHITE_BRIGHT);
                if(sess instanceof Admin){
                    sihm = new ServiceIHMAdmin(bank);
                    sihm.menuGlobal(sc);
                }else{
//                    ServiceClient scl= new ServiceClient((Client) sess,bank);
//                    scl.setBank(bank);
                    sihm = new ServiceIHMClient((Client) sess,bank);
                    sihm.menuGlobal(sc);
                }
            }
        }while(sess==null);
    }

    @Override
    public void SeDeconnecter() {
        Scanner sc=new Scanner(System.in);
        seConnecter(sc);
    }
}
