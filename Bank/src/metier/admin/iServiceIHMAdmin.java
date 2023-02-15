package metier.admin;

import metier.authentification.iServiceIHM;

import java.util.Scanner;

public interface iServiceIHMAdmin extends iServiceIHM {
    void menuModification(Scanner sc);
    void menuRecherche(Scanner sc);
    void menuInformations(Scanner sc);
    void menuAjout(Scanner sc);
    void menuSuppression(Scanner sc);
    void tableauDeBord(Scanner sc);
    void menuTrie(Scanner sc);
    void menuComptabilite(Scanner sc);
}
