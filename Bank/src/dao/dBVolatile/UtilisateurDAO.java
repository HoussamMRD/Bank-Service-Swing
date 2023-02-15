package dao.dBVolatile;

import dao.IUtilisateurDAO;
import presentation.model.Admin;
import presentation.model.Banque;
import presentation.model.Utilisateur;

import java.util.List;

public class UtilisateurDAO implements IUtilisateurDAO {

    Banque banque;

    public void setBanque(Banque banque) {
        this.banque = banque;
    }

    public Banque getBanque() {
        return banque;
    }

    public UtilisateurDAO(){}
    public UtilisateurDAO(Banque banque){this.banque = banque;}

    @Override
    public List<Utilisateur> findAll() {
        return null;
    }

    @Override
    public Utilisateur findById(Long aLong) {
        return null;
    }

    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public List<Utilisateur> saveAll(List<Utilisateur> t) {
        return null;
    }

    @Override
    public Utilisateur update(Utilisateur utilisateur) {
        return null;
    }

    @Override
    public boolean deleteByID(Long aLong) {
        return false;
    }

    @Override
    public boolean delete(Utilisateur utilisateur) {
        return false;
    }

//    @Override
//    public boolean delete(Long aLong) {
//        return false;
//    }

    @Override
    public Utilisateur findByLoginAndPass(String login, String pass) {

        Utilisateur  user = null;

        Admin admin = Admin.getInstance();

        if(admin.getLogin().equals(login)&& admin.getMotDePasse().equals(pass))
                user = admin;
        else{
            user =
                    banque.getClientsDeBanque()
                            .stream()
                            .filter(client -> {
                                return client.getLogin().equals(login)
                                        && client.getMotDePasse().equals(pass);
                            })
                            .findFirst()
                            .orElse(null);
        }
        return  user;
    }
}
