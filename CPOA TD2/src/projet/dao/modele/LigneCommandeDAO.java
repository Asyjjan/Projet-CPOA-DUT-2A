package projet.dao.modele;

import java.util.ArrayList;

import projet.dao.DAO;
import projet.metier.LigneCommande;

public interface LigneCommandeDAO extends DAO<LigneCommande> {

	boolean create(LigneCommande objet);

	boolean update(LigneCommande objet);

	boolean delete(LigneCommande objet);

	LigneCommande getById(int id);

	ArrayList<LigneCommande> findAll();
}
