package projet.dao.modele;

import java.sql.SQLException;
import java.util.ArrayList;

import projet.dao.DAO;
import projet.metier.LigneCommande;

public interface LigneCommandeDAO {

	boolean create(int idc, LigneCommande objet) throws SQLException;

	boolean delete(int idc, int idp) throws SQLException;

	ArrayList<LigneCommande> findAll() throws SQLException;

	LigneCommande getById(int idcommande, int idproduit) throws SQLException;

	boolean update(LigneCommande objet) throws SQLException;

}
