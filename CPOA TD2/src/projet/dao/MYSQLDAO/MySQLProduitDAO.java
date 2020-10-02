package projet.dao.MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import projet.*;
import projet.dao.modele.ProduitDAO;
import projet.menu.Connexion;
import projet.metier.Produit;

public class MySQLProduitDAO implements ProduitDAO {

	private static MySQLProduitDAO instance;

	private MySQLProduitDAO() {
	}

	public static MySQLProduitDAO getInstance() {
		if (instance == null) {
			instance = new MySQLProduitDAO();
		}
		return instance;
	}

	@Override
	public ArrayList<Produit> findAll() throws SQLException {
		ArrayList<Produit> liste = new ArrayList<Produit>();
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		ResultSet res = requete.executeQuery("SELECT * FROM Produit");
		while (res.next()) {
			int idprod = res.getInt(1);
			String nom = res.getString(2);
			String description = res.getString(3);
			float tarif = res.getFloat(4);
			String visuel = res.getString(5);
			int idcateg = res.getInt(6);
			Produit produit = new Produit(idprod, nom, description, tarif, visuel, idcateg);
			liste.add(produit);
		}
		return liste;
	}

	@Override
	public boolean create(Produit objet) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"INSERT INTO 	Produit (nom, description, tarif, visuel, id_categorie) VALUES (?,?,?,?,?)");
		requete.setString(1, objet.getNom());
		requete.setString(2, objet.getDesc());
		requete.setFloat(3, objet.getTarif());
		requete.setString(4, objet.getVisuel());
		requete.setInt(5, objet.getIdcateg());
		nbLignes = requete.executeUpdate();
		return nbLignes == 1;
	}

	@Override
	public boolean update(Produit objet) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"UPDATE Produit SET nom = ?, description = ?, tarif = ?, visuel = ?, id_categorie = ? WHERE Produit.id_produit = ?");
		requete.setString(1, objet.getNom());
		requete.setString(2, objet.getDesc());
		requete.setFloat(3, objet.getTarif());
		requete.setString(4, objet.getVisuel());
		requete.setInt(5, objet.getIdcateg());
		requete.setInt(6, objet.getIdprod());
		nbLignes = requete.executeUpdate();
		return nbLignes == 1;
	}

	@Override
	public boolean delete(Produit objet) throws SQLException{
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Produit WHERE Produit.id_produit = ?");
		requete.setInt(1, objet.getIdprod());
		nbLignes = requete.executeUpdate();
		return nbLignes == 1;
	}

	@Override
	public Produit getById(int id) throws SQLException {
		String nom = "";
		String description = "";
		float tarif = 0;
		String visuel = "";
		int idcateg = 0;
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		ResultSet res = requete.executeQuery("SELECT * FROM Produit WHERE Produit.id_produit = ?");
		if (res.next()) {
			nom = res.getString(2);
			description = res.getString(3);
			tarif = res.getFloat(4);
			visuel = res.getString(5);
			idcateg = res.getInt(6);
		
	}
		Produit produit = new Produit(id, nom, description, tarif, visuel, idcateg);
		return produit;
	}

}