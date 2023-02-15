package metier.clients;

import java.util.Scanner;

public interface iServiceClient {

    boolean versement(Scanner sc);
    boolean retrait  (Scanner sc);

    boolean retrait  (int choixRetrait);
    boolean virement (Scanner sc);
    boolean modifierProfile(Integer ch,Scanner sc);
    void dernieresOperations();
    Double afficherSolde();
    Integer choisirCompte(Scanner sc);

    void afficherTicket();
}
