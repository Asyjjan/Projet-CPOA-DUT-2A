package projet.dao.modele;

import java.sql.SQLException;
import java.util.ArrayList;

import projet.dao.DAO;
import projet.metier.Client;

public interface ClientDAO extends DAO<Client> {

	boolean create(Client objet) throws SQLException;

	boolean update(Client objet) throws SQLException;

	boolean delete(Client objet) throws SQLException;

	Client getById(int id) throws SQLException;

	ArrayList<Client> findAll() throws SQLException;
}
