package metier.admin;

import presentation.model.Client;
import presentation.model.Compte;
import presentation.model.TableauDeBord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public interface iServiceAdmin {

    Client nouveauClient(Scanner sc);
    Client nouveauCompteClientExistant(Scanner sc);

    Client chercherClientParId(Long id);
    List<Client> chercherClientParNom(String nom);
    List<Client> chercherClientParPrenom(String prenom);
    Client chercherClientParCin(String cin);
    Client chercherClientParEmail(String email);

    Compte chercherCompteParId(String idCompte);
    List<Compte> chercherCompteParSolde(double solde);
    List<Compte> chercherCompteParDateCreation(LocalDate date);
    List<Compte> chercherCompteParProprietaire(Client propriétaire);

    Client modifierClient(String filtre, Scanner sc);
    boolean supprimerClient(Long id);

    TableauDeBord calculerEtAfficherStatistiques();

    List<Client> trierClientParNom();
    List<Client> trierClientParCin();
    List<Client> trierClientParEmail();
    List<Client> trierClientParSoldeCompte();
    List<Client> trierClientParID();
    List<Compte> trierComptesParSolde();
    List<Compte> trierComptesParDateDeCreation();
    List<Compte> trierComptesParNomProprietaire();
}
