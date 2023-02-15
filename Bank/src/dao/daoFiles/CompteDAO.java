package dao.daoFiles;

import dao.IDao;
import presentation.model.Client;
import presentation.model.Compte;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class CompteDAO implements IDao<Compte,String> {
    @Override
    public List<Compte> findAll() {
        List<Compte> comptes = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(FileBasePaths.ACCOUNT_TABLE, StandardCharsets.UTF_8);
            lines.remove(0);
            if(!lines.isEmpty())
                comptes=
                        lines
                                .stream()
                                .map(line->{
                                    Compte cp = null;
                                    StringTokenizer st = new StringTokenizer(line, "\t");
                                    String nbCp = st.nextToken();
                                    double   solde          =   Double.parseDouble(st.nextToken());
                                    String   dateCreation           =   st.nextToken();
                                    long   prop            =  Long.parseLong(st.nextToken());
                                    cp = new Compte(solde,new ClientDAO().findById(prop));
                                    cp.setNumeroCompte(nbCp);
                                    cp.setDateCreation(LocalDate.parse(dateCreation));
                                    return cp;
                                })
                                .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return comptes;
    }

    @Override
    public Compte findById(String s) {
        return findAll().stream()
            .filter(compte -> Objects.equals(compte.getNumeroCompte(), s))
            .findFirst()
            .orElse(null);
    }

    public Compte findByNbCompte(String nbCp) {
        return findAll().stream()
                .filter(compte -> Objects.equals(compte.getNumeroCompte(), nbCp))
                .findFirst()
                .orElse(null);
    }




    @Override
    public Compte save(Compte compte) {
        String compteStr =
                compte.getNumeroCompte()+ "\t\t" +
                        compte.getSolde()+ "\t\t" +
                compte.getDateCreation()+ "\t\t" +
                compte.getProprietaire().getId()+ "\t\t\n";
        try {
            Files.writeString(FileBasePaths.ACCOUNT_TABLE, compteStr, StandardOpenOption.APPEND);
        }
        catch (IOException e) { e.printStackTrace();}
        return compte;
    }

    @Override
    public List<Compte> saveAll(List<Compte> t) {
        return
                t
                        .stream()
                        .map(compte -> save(compte))
                        .collect(Collectors.toList());
    }

    @Override
    public Compte update(Compte compte) {
        List<Compte> accountsUpdated =
                findAll()
                        .stream()
                        .map(compte1 -> {
                            if(Objects.equals(compte1.getNumeroCompte(), compte.getNumeroCompte()))
                                return compte;
                            else
                                return compte1;
                        })
                        .collect(Collectors.toList());
        try { Files.deleteIfExists(FileBasePaths.INDEX_ACCOUNT);} catch (IOException e) {e.printStackTrace();}
        FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE, FileBasePaths.ACCOUNT_TABLE_HEADER);
        saveAll(accountsUpdated);
        return compte;
    }

    @Override
    public boolean deleteByID(String s) {
        var comptes = findAll();
        boolean deleted  =
                comptes.remove(findById(s));
        if(deleted) {
            FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE, FileBasePaths.ACCOUNT_TABLE_HEADER);
            saveAll(comptes);
        }
        return deleted;
    }

    public List<Compte> findByIdProp(Long id){
        List<Compte> lst = new ArrayList<>();
        for(Compte cc : findAll())
            if (cc.getProprietaire().getId().equals(id))
                lst.add(cc);
        return lst;
    }

    public void deleteAccountsOfClient(Long id){
//        List<Compte> comptes = findAll();
        for(Compte cc : findAll()){
            if (cc.getProprietaire().getId().equals(id))
                delete(cc);
        }
    }

    public boolean deleteByNbCompte(String nb){
        var comptes = findAll();
        boolean deleted  =
                comptes.remove(findByNbCompte(nb));
        if(deleted) {
            FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE, FileBasePaths.ACCOUNT_TABLE_HEADER);
            saveAll(comptes);
        }
        return deleted;
    }

    @Override
    public boolean delete(Compte compte) {
        var comptes = findAll();
        boolean deleted  = comptes.remove(compte);
        if(deleted) {
            FileBasePaths.changeFile(FileBasePaths.ACCOUNT_TABLE, FileBasePaths.ACCOUNT_TABLE_HEADER);
            saveAll(comptes);
        }
        return deleted;
    }

    public List<Compte> trierPar(String str) {
        List<Compte> lst = new ArrayList<>();
        lst.addAll(findAll());
        if(Objects.equals(str, "Solde"))
            lst.sort((c1, c2) -> Double.compare(c1.getSolde(), c2.getSolde()));
        if(Objects.equals(str, "Date de creation"))
            lst.sort((c1, c2) -> c1.getDateCreation().compareTo(c2.getDateCreation()));
        if(Objects.equals(str, "ID proprietaire"))
            lst.sort((c1, c2) -> c1.getProprietaire().getId().compareTo(c2.getProprietaire().getId()));
        return lst;
    }

    public List<Compte> findByKeywordLike(String keyWord){
        List<Compte> comptes = findAll();
        return
                comptes.stream().filter(compte ->
                        compte.getNumeroCompte().contains(keyWord) ||
                                compte.getSolde().toString().contains(keyWord.toLowerCase()) ||
                                compte.getSolde().equals(Double.parseDouble(keyWord.toLowerCase())) ||
                                compte.getProprietaire().getId().toString().equals(keyWord)||
                                compte.getDateCreation().toString().contains(keyWord)||
                                compte.getDateCreation().toString().equals(keyWord)
                ).collect(Collectors.toList());
    }

}
