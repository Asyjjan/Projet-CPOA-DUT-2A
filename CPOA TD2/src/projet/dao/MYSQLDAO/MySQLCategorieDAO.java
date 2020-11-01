package projet.dao.MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import projet.dao.modele.CategorieDAO;
import projet.metier.*;
import projet.utilitaire.Connexion;

public class MySQLCategorieDAO implements CategorieDAO{

	private static MySQLCategorieDAO instance;

	private MySQLCategorieDAO() {
	}

	public static MySQLCategorieDAO getInstance() {
		if (instance == null) {
			instance = new MySQLCategorieDAO();
		}
		return instance;
	}

	@Override
	public boolean create(Categorie objet) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"INSERT INTO 	Categorie (id_categorie, titre, visuel) VALUES (?,?,?)");
		requete.setInt(1, objet.getIdcateg());
		requete.setString(2, objet.getTitre());
		requete.setString(3, objet.getVisuel());
		nbLignes = requete.executeUpdate();
		return nbLignes == 1;
	}

	@Override
	public boolean update(Categorie objet) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"UPDATE Categorie SET titre = ?, visuel = ? WHERE id_categorie = ?");
		requete.setString(1, objet.getTitre());
		requete.setString(2, objet.getVisuel());
		requete.setInt(3, objet.getIdcateg());
		nbLignes = requete.executeUpdate();
		return nbLignes == 1;
	}

	@Override
	public boolean delete(Categorie objet) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Categorie WHERE id_categorie = ?");
		requete.setInt(1, objet.getIdcateg());
		nbLignes = requete.executeUpdate();
		return nbLignes == 1;
	}

	@Override
	public Categorie getById(int id) throws SQLException {
		String titre = "";
		String visuel = "";
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		ResultSet res = requete.executeQuery("SELECT * FROM Categorie WHERE id_categorie =" + id);
		if (res.next()) {
			titre = res.getString(2);
			visuel = res.getString(3);
		}
		Categorie categorie = new Categorie(id, titre, visuel);
		return categorie;
	}

	@Override
	public ArrayList<Categorie> findAll() throws SQLException {
		ArrayList<Categorie> liste = new ArrayList<Categorie>();
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		ResultSet res = requete.executeQuery("SELECT * FROM Categorie");
		while (res.next()) {
			int idCateg = res.getInt(1);
			String titre = res.getString(2);
			String visuel = res.getString(3);
			Categorie categorie = new Categorie(idCateg, titre, visuel);
			liste.add(categorie);
		}
		return liste;
	}
}
