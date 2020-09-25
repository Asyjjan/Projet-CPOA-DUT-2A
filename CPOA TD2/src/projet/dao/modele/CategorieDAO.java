package projet.dao.modele;

import java.util.ArrayList;

import projet.dao.DAO;
import projet.metier.Categorie;

public interface CategorieDAO extends DAO<Categorie>{

	boolean create(Categorie objet);

	boolean update(Categorie objet);

	boolean delete(Categorie objet);

	Categorie getById(int id);

	ArrayList<Categorie> findAll();

}
