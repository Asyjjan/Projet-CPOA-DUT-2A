package projet.dao.modele;

import java.util.ArrayList;

import projet.dao.DAO;
import projet.metier.Client;

public interface ClientDAO extends DAO<Client> {

	boolean create(Client objet);

	boolean update(Client objet);

	boolean delete(Client objet);

	Client getById(int id);

	ArrayList<Client> findAll();
}
