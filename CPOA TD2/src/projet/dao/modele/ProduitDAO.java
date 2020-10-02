package projet.dao.modele;

import java.sql.SQLException;
import java.util.ArrayList;

import projet.dao.DAO;
import projet.metier.Produit;

public interface ProduitDAO extends DAO<Produit> {

	boolean create(Produit objet)throws SQLException;

	boolean update(Produit objet)throws SQLException;

	boolean delete(Produit objet)throws SQLException;

	Produit getById(int id)throws SQLException;

	ArrayList<Produit> findAll() throws SQLException;
}
