package metier.clients;

import metier.authentification.iServiceIHM;

import java.util.Scanner;

public interface iServiceIHMClient extends iServiceIHM {
    void menuModification(Scanner sc);
    void menuRetrait(Scanner sc);
    void menuInformations(Scanner sc);
}
