package projet.dao.modele;

import java.util.ArrayList;

import projet.dao.DAO;
import projet.metier.Produit;

public interface ProduitDAO extends DAO<Produit> {

	boolean create(Produit objet);

	boolean update(Produit objet);

	boolean delete(Produit objet);

	Produit getById(int id);

	ArrayList<Produit> findAll();
}
