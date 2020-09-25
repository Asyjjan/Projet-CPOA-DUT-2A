package projet.dao.modele;

import java.util.ArrayList;

import projet.dao.DAO;
import projet.metier.Commande;

public interface CommandeDAO extends DAO<Commande> {

	boolean create(Commande objet);

	boolean update(Commande objet);

	boolean delete(Commande objet);

	Commande getById(int id);

	ArrayList<Commande> findAll();
}
