package projet.dao.modele;

import java.sql.SQLException;
import java.util.ArrayList;

import projet.dao.DAO;
import projet.metier.Categorie;

public interface CategorieDAO extends DAO<Categorie>{

	boolean create(Categorie objet) throws SQLException;

	boolean update(Categorie objet) throws SQLException;

	boolean delete(Categorie objet) throws SQLException;

	Categorie getById(int id) throws SQLException;

	ArrayList<Categorie> findAll() throws SQLException;

}
