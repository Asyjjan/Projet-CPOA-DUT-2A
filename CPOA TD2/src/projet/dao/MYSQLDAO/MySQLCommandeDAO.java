package projet.dao.MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import projet.dao.modele.CommandeDAO;
import projet.menu.Connexion;
import projet.metier.*;

public class MySQLCommandeDAO {

	private static MySQLCommandeDAO instance;

	private MySQLCommandeDAO() {
	}

	public static MySQLCommandeDAO getInstance() {
		if (instance == null) {
			instance = new MySQLCommandeDAO();
		}
		return instance;
	}

	public static void create(int id_commande,LocalDate datecommande,int id_client) {

		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement insertion = laConnexion.createStatement();

			PreparedStatement requete = laConnexion.prepareStatement("insert into Commande (id_commande, date_commande, id_client) values (?,?,?)");
			requete.setInt(1, id_commande);
			requete.setDate(2, java.sql.Date.valueOf(datecommande));
			requete.setInt(3, id_client);
			int resu = requete.executeUpdate();
			System.out.println("Insï¿½ration faite.");

		}catch (SQLException sqle) {
			System.out.println("Problï¿½me insert " + sqle.getMessage());
		}

	}

	public static void delete(int idcommande) throws SQLException {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("delete from Commande where id_commande=?");
			requete.setInt(1, idcommande);
			int res = requete.executeUpdate();
			System.out.println("Suppresion faite.");
		}catch (SQLException sqle) {
			System.out.println("Problï¿½me delete " + sqle.getMessage());
		}
	}

	public static void update(int idcommande, LocalDate datecommande, int idclient) {

		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement modification = laConnexion.createStatement();

			PreparedStatement requete = laConnexion.prepareStatement("Update Commande set date_commande=?, id_client=? where id_commande=?");
			requete.setDate(1, java.sql.Date.valueOf(datecommande));
			requete.setInt(2,idclient);	
			requete.setInt(3, idcommande);
			int resu = requete.executeUpdate();
			System.out.println("Modification faite.");

		}catch (SQLException sqle) {
			System.out.println("Problï¿½me modification " + sqle.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Commande> Commande(){
		@SuppressWarnings("rawtypes")
		ArrayList<Commande> p= new ArrayList();
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("select * from Commande");
			ResultSet res = requete.executeQuery();

			while (res.next()) {
				HashMap<Produit, LigneCommande> ligneCommande = new HashMap<Produit, LigneCommande>();
				p.add(new Commande (res.getInt(1), res.getDate(2).toLocalDate(), res.getInt(3), ligneCommande));
			}

		}catch (SQLException sqle) {
			System.out.println("Problï¿½me ArrayList " + sqle.getMessage());
		}
		return p;

	}

	public Commande getById(int id) throws SQLException {
		Commande commande = null;

		HashMap<Produit, LigneCommande> ligneCommande = new HashMap<Produit, LigneCommande>();

		Connection laConnexion = Connexion.creeConnexion();

		//requete pour obtenir la commande desiree
		PreparedStatement requete = laConnexion.prepareStatement("select * from `Commande` where id_commande=" + id);
		ResultSet res = requete.executeQuery();

		if (res.next()) {
			//requete pour obtenir toute les lignes de commandes concern�es par cette commande
			PreparedStatement requeteLc = laConnexion.prepareStatement("select * from `Ligne_Commande` where id_commande=" + id);
			ResultSet resLc = requeteLc.executeQuery();

			while (resLc.next()) {
				//requete pour obtenir pour les produits concern�s par la commande
				PreparedStatement requeteProd = laConnexion.prepareStatement("select * from `Produit` where id=" + resLc.getInt(2));
				ResultSet resProd = requeteProd.executeQuery();

				ligneCommande.put(new Produit(resProd.getInt(1), resProd.getString(2), resProd.getString(3), resProd.getFloat(4), resProd.getString(5), resProd.getInt(6)), 
						new LigneCommande(resLc.getInt(1), resLc.getInt(2), resLc.getInt(3), resLc.getFloat(4)));
			}

			commande = new Commande(res.getInt(1), res.getDate(2).toLocalDate(), res.getInt(3), ligneCommande);
		}

		if (laConnexion != null)
			laConnexion.close();

		return commande;
	}
}
