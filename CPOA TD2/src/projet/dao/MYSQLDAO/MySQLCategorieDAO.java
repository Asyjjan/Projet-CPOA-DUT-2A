package projet.dao.MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import projet.dao.modele.CategorieDAO;
import projet.menu.Connexion;
import projet.metier.*;

public class MySQLCategorieDAO{

	private static MySQLCategorieDAO instance;

	private MySQLCategorieDAO() {
	}

	public static MySQLCategorieDAO getInstance() {
		if (instance == null) {
			instance = new MySQLCategorieDAO();
		}
		return instance;
	}

	public static void create(int idcateg, String titrecateg, String visuel) {

		try {

			Connection laConnexion = Connexion.creeConnexion();
			Statement insertion = laConnexion.createStatement();

			PreparedStatement requete = laConnexion.prepareStatement("insert into Categorie (id_categorie, titre, visuel) values (?,?,?)");
			requete.setInt(1, idcateg);
			requete.setString(2, titrecateg);
			requete.setString(3, visuel);
			int resu = requete.executeUpdate();
			System.out.println("Ins�ration faite.");

		}catch (SQLException sqle) {
			System.out.println("Probl�me insert " + sqle.getMessage());
		}

	}
	public static void delete(int idcateg) throws SQLException {

		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("delete from Categorie where id_categorie=?");
			requete.setInt(1, idcateg);
			int res = requete.executeUpdate();
			System.out.println("Suppresion faite.");
		}catch (SQLException sqle) {
			System.out.println("Probl�me delete " + sqle.getMessage());
		}
	}

	public static void update(int idcateg, String titrecateg, String visuel) {

		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement modification = laConnexion.createStatement();

			PreparedStatement requete = laConnexion.prepareStatement("Update Categorie set titre=?,visuel=? where id_categorie=?");
			requete.setString(1,titrecateg);
			requete.setString(2,visuel);	
			requete.setInt(3, idcateg);
			int resu = requete.executeUpdate();
			System.out.println("Modification faite.");

		}catch (SQLException sqle) {
			System.out.println("Probl�me modification " + sqle.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Categorie> Categorie() throws SQLException{
		ArrayList<Categorie> liste = new ArrayList<Categorie>();
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		ResultSet res = requete.executeQuery("SELECT * FROM Categorie");
		while (res.next()) {
			int id = res.getInt(1);
			String titre = res.getString(2);
			String visuel = res.getString(3);
			Categorie categ = new Categorie(id, titre, visuel);
			liste.add(categ);
		}
		return liste;
	}


}
