package dao.dbFiles;

import dao.IUtilisateurDAO;
import presentation.model.Utilisateur;

import java.util.List;

public class UtilisateurDAOFile implements IUtilisateurDAO {

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



    @Override
    public Utilisateur findByLoginAndPass(String login, String pass) {
        return null;
    }
}
