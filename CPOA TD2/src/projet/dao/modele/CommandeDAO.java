package projet.dao.modele;

import java.sql.SQLException;
import java.util.ArrayList;

import projet.dao.DAO;
import projet.metier.Commande;

public interface CommandeDAO extends DAO<Commande> {

	boolean create(Commande objet)throws SQLException;

	boolean update(Commande objet)throws SQLException;

	boolean delete(Commande objet) throws SQLException;

	Commande getById(int id)throws SQLException;

	ArrayList<Commande> findAll()throws SQLException;
}
