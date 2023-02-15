package dao.daoFiles;

import dao.IDao;
import presentation.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import static dao.daoFiles.FileBasePaths.LOGSFOLDER;
import static metier.Colors.WHITE_BRIGHT;


public class ClientDAO implements IDao<Client,Long> {
    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(FileBasePaths.CLIENT_TABLE, StandardCharsets.UTF_8);
            lines.remove(0);
            if(!lines.isEmpty())
                clients=
                        lines
                                .stream()
                                .map(line->{
                                    Client cl = null;
                                    StringTokenizer st = new StringTokenizer(line, "\t");
                                    long     id              =   Long.parseLong(st.nextToken());
                                    String   nom             =   st.nextToken();
                                    String   prenom          =   st.nextToken();
                                    String   login           =   st.nextToken();
                                    String   pass            =   st.nextToken();
                                    String   cin             =   st.nextToken();
                                    String   email           =   st.nextToken();
                                    String   tel             =   st.nextToken();
                                    String   sexe            =   st.nextToken();
                                    Sexe sex = null;
                                    if(email.equalsIgnoreCase("NULL")) email ="";
                                    if(tel.equalsIgnoreCase("NULL")) tel ="";
                                    if(!sexe.equalsIgnoreCase("NULL")) {
                                        if(sexe.equalsIgnoreCase("HOMME")) sex = Sexe.HOMME;
                                        else sex = Sexe.FEMME;
                                    }
                                    cl = new Client(login, pass, nom, prenom, email,cin, tel, sex);
                                    cl.setId(id);
                                    return cl;
                                })
                                .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clients;
    }

    @Override
    public Client findById(Long idClient) {
        return findAll().stream()
                .filter(client -> Objects.equals(client.getId(), idClient))
                .findFirst()
                .orElse(null);

    }

    public List<String> getLogs(Client client){
        List<String> lst = new ArrayList<>();
        if(findByCIN(client.getCin())!=null) {
            String clientNBR = "client_ID";
            clientNBR = clientNBR.concat(String.valueOf(client.getId()));
            clientNBR = clientNBR.concat(".txt");
            File fileLog = new File(LOGSFOLDER.toString(), clientNBR);
            try (BufferedReader br = new BufferedReader(new FileReader(fileLog))) {
                String line;
                while ((line = br.readLine()) != null) {
                    lst.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lst;
    }

    public Client findByCIN(String cin) {
        return findAll().stream()
                .filter(client -> Objects.equals(client.getCin(), cin))
                .findFirst()
                .orElse(null);
    }
    public List<Client> trierPar(String str){
        List<Client> lst = new ArrayList<>();
        lst.addAll(findAll());
        if(Objects.equals(str, "Nom"))
            lst.sort((c1, c2) -> c1.getNom().compareTo(c2.getNom()));
        if(Objects.equals(str, "CIN"))
            lst.sort((c1, c2) -> c1.getCin().compareTo(c2.getCin()));
        if(Objects.equals(str, "Email"))
            lst.sort((c1, c2) -> c1.getEmail().compareTo(c2.getEmail()));
        return lst;
    }

    public Client findByLogAndPass(String log,String pass) {
        return findAll().stream()
                .filter(client -> Objects.equals(client.getLogin(), log) && Objects.equals(client.getMotDePasse(), pass))
                .findFirst()
                .orElse(null);
    }

    public Client DoublonsByLogAndPass(Client c,String log,String pass) {
        for(Client cc : findAll()){
            if(!cc.getId().equals(c.getId()))
                if(cc.getLogin().equals(log) && cc.getMotDePasse().equals(pass))
                    return cc;
        }
        return null;
    }

    private long getIncrementedId(){
        var clientList = findAll();
        var maxId = 1L;
        if(!clientList.isEmpty()) {
            maxId = findAll().stream().map(client -> client.getId()).max((id1,id2)-> id1.compareTo(id2)).get();
            maxId++;
        }
        return maxId;
    }


    @Override
    public Client save(Client client) {
        // Solution D'incrémentation 1
        Long id = getIncrementedId();
        // Solution D'incrémentation 2
        //   id = getIncrementedIdByIndexFile(FileBasePaths.INDEX_CLIENT);
        String clientStr =  id + "\t\t\t" +
                client.getNom()+ "\t\t" +
                client.getPrenom()+ "\t\t" +
                client.getLogin()+ "\t\t" +
                client.getMotDePasse()+ "\t\t" +
                client.getCin()+ "\t\t\t" +
                (client.getEmail()!=null&&client.getEmail().trim().length()!=0?client.getEmail():"NULL")+"\t\t"+
                (client.getTel()!=null&&client.getTel().trim().length()!=0?client.getTel():"NULL")+ "\t\t" +
                (client.getSexe()!=null?client.getSexe():"NULL")+ "\t\t" + "\n";
        try {
            if(findAll().isEmpty()){
                Files.writeString(FileBasePaths.CLIENT_TABLE, clientStr, StandardOpenOption.APPEND);
//                System.out.println(CYAN_BOLD_BRIGHT+"Client n ° "+ id + " a été ajouté avec succès ^_^");
                System.out.println(WHITE_BRIGHT);
                client.setId(id);
                return client;
            }else{
                for (Client cl : findAll()){
                    if(!(cl.getLogin().equals(client.getLogin()) && cl.getMotDePasse().equals(client.getMotDePasse())) && !cl.getCin().equals(client.getCin())){
                        Files.writeString(FileBasePaths.CLIENT_TABLE, clientStr, StandardOpenOption.APPEND);
                        client.setId(id);
                        return client;
                    }
                    else return null;
                }
            }
        }
        catch (IOException e) { e.printStackTrace();}
        return client;
    }

    public Client saveWithID(Client client) {

        String clientStr =  client.getId() + "\t\t\t" +
                client.getNom()+ "\t" +
                client.getPrenom()+ "\t\t" +
                client.getLogin()+ "\t\t" +
                client.getMotDePasse()+ "\t\t" +
                client.getCin()+ "\t\t\t" +
                (client.getEmail()!=null&&client.getEmail().trim().length()!=0?client.getEmail():"NULL")+ "\t" +
                (client.getTel()!=null&&client.getTel().trim().length()!=0?client.getTel():"NULL")+ "\t\t" +
                (client.getSexe()!=null?client.getSexe():"NULL")+ "\t\tNULL\n" ;

        try {
            Files.writeString(FileBasePaths.CLIENT_TABLE, clientStr, StandardOpenOption.APPEND);
            System.out.println("Client n ° "+ client.getId() + " a été ajouté avec succès ^_^");
        }
        catch (IOException e) { e.printStackTrace();}

        return client;
    }

    @Override
    public List<Client> saveAll(List<Client> listeClients) {
        return
                listeClients
                        .stream()
                        .map(client -> save(client))
                        .collect(Collectors.toList());
    }

    public List<Client> saveAllWithIds(List<Client> listeClients) {
        return
                listeClients
                        .stream()
                        .map(client -> saveWithID(client))
                        .collect(Collectors.toList());
    }

    @Override
    public Client update(Client newClient) {

        List<Client> clientsUpdated =
                findAll()
                        .stream()
                        .map(client -> {
                            if(Objects.equals(client.getId(), newClient.getId()))
                                return newClient;
                            else
                                return client;
                        })
                        .collect(Collectors.toList());


        try { Files.deleteIfExists(FileBasePaths.INDEX_CLIENT);} catch (IOException e) {e.printStackTrace();}
        FileBasePaths.changeFile(FileBasePaths.CLIENT_TABLE, FileBasePaths.CLIENT_TABLE_HEADER);

        saveAll(clientsUpdated);

        return newClient;
    }

    @Override
    public boolean deleteByID(Long aLong) {
        CompteDAO compteDAO = new CompteDAO();
        compteDAO.deleteAccountsOfClient(aLong);
        var clients = findAll();
        String clientNBR = "client_ID";
        clientNBR = clientNBR.concat(String.valueOf(aLong));
        clientNBR = clientNBR.concat(".txt");
        boolean deleted  = clients.remove(findById(aLong));
        if(deleted) {
            FileBasePaths.changeFile(FileBasePaths.CLIENT_TABLE, FileBasePaths.CLIENT_TABLE_HEADER);
            saveAllWithIds(clients);
            Path LOGS_FILE_BASE_FOLDER = Paths.get(LOGSFOLDER.toString(),clientNBR);
            try {
                Files.deleteIfExists(LOGS_FILE_BASE_FOLDER);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return deleted;
    }

    public double getSoldeTotal(Long id){
        double mt = 0.0;
        for(Client client : findAll()){
            if(client.getId().equals(id)){
                for(Compte compte : new CompteDAO().findAll()){
                    if(compte.getProprietaire().getId().equals(id)){
                        mt+=compte.getSolde();
                    }
                }
            }
        }
        return mt;
    }

    public TableauDeBord calculerEtAfficherStatistiques() {
        TableauDeBord res = new TableauDeBord();
        Double solde=0.0,soldemin=0.0;
        String nom=null;
        long nbrF=0,nbrH=0,nbrC=0;
        res.setNombreTotaleClient((long)findAll().size());
        for (Client c : findAll()) {
            if(c.getSexe() == Sexe.HOMME)
                nbrH++;
            else
                nbrF++;
            for (Compte x : new CompteDAO().findAll()) {
                nbrC++;
                if (x.getSolde() > solde) {
                    solde = x.getSolde();
                    nom = x.getProprietaire().getNomComplet();
                }
            }
        }
        soldemin = solde;
        for (Client c : findAll()) {
            for (Compte x : new CompteDAO().findAll()){
                if(c.getId().equals(x.getProprietaire().getId())){
                    if(x.getSolde()<soldemin)
                        soldemin=x.getSolde();
                }
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
    public boolean delete(Client clientToDelete) {
        CompteDAO compteDAO = new CompteDAO();
        compteDAO.deleteAccountsOfClient(clientToDelete.getId());
        String clientNBR = "client_ID";
        clientNBR = clientNBR.concat(String.valueOf(clientToDelete.getId()));
        clientNBR = clientNBR.concat(".txt");
        var clients = findAll();
        boolean deleted  = clients.remove(clientToDelete);
        if(deleted) {
            FileBasePaths.changeFile(FileBasePaths.CLIENT_TABLE, FileBasePaths.CLIENT_TABLE_HEADER);
            saveAllWithIds(clients);
            Path LOGS_FILE_BASE_FOLDER = Paths.get(LOGSFOLDER.toString(),clientNBR);
            try {
                Files.deleteIfExists(LOGS_FILE_BASE_FOLDER);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return deleted;
    }

    public Boolean deleteById(Long idClient) {
        CompteDAO compteDAO = new CompteDAO();
        compteDAO.deleteAccountsOfClient(idClient);
        String clientNBR = "client_ID";
        clientNBR = clientNBR.concat(String.valueOf(idClient));
        clientNBR = clientNBR.concat(".txt");
        var clients = findAll();
        boolean deleted = clients.remove(findById(idClient));
        if(deleted) {
            FileBasePaths.changeFile(FileBasePaths.CLIENT_TABLE, FileBasePaths.CLIENT_TABLE_HEADER);
            saveAllWithIds(clients);
            Path LOGS_FILE_BASE_FOLDER = Paths.get(LOGSFOLDER.toString(),clientNBR);
            try {
                Files.deleteIfExists(LOGS_FILE_BASE_FOLDER);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return deleted;
    }

    public List<Client> findByKeywordLike(String keyWord){
        List<Client> clients = findAll();
        return
                clients.stream().filter(client ->
                                        client.getId().toString().equals(keyWord) ||
                                        client.getNom().toLowerCase().contains(keyWord.toLowerCase()) ||
                                        client.getPrenom().toLowerCase().contains(keyWord.toLowerCase()) ||
                                        client.getLogin().equals(keyWord) ||
                                        client.getMotDePasse().equals(keyWord) ||
                                        client.getCin().equalsIgnoreCase(keyWord) ||
                                        client.getEmail().equalsIgnoreCase(keyWord) ||
                                        client.getTel().equals(keyWord) ||
                                        client.getSexe().toString().toLowerCase().equalsIgnoreCase(keyWord)
                        ).collect(Collectors.toList());
    }
}
