package projet.dao.MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import projet.menu.Connexion;
import projet.metier.LigneCommande;

public class MySQLLigneCommandeDAO {

	public static void create(int idcommande, int idproduit, int quantite, float tarifunitaire) {

		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement insertion = laConnexion.createStatement();

			PreparedStatement requete = laConnexion.prepareStatement(
					"insert into Ligne_commande (id_commande,id_produit,quantite,tarif_unitaire) values (?,?,?,?)");
			requete.setInt(1, idcommande);
			requete.setInt(2, idproduit);
			requete.setInt(3, quantite);
			requete.setFloat(4, tarifunitaire);
			int res = requete.executeUpdate();
			System.out.println("Ins�ration faite.");

		} catch (SQLException sqle) {
			System.out.println("Probl�me insert " + sqle.getMessage());
		}

	}

	public static void delete(int idcommande, int idproduit) throws SQLException {
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion
					.prepareStatement("delete from Ligne_commande where id_commande=? and id_produit=?");
			requete.setInt(1, idcommande);
			requete.setInt(2, idproduit);
			int res = requete.executeUpdate();
			System.out.println("Suppresion faite.");
		} catch (SQLException sqle) {
			System.out.println("Probl�me delete " + sqle.getMessage());
		}
	}

	public static void update(int idcommande, int idproduit, int quantite, float tarifunitaire) {

		try {
			Connection laConnexion = Connexion.creeConnexion();
			Statement modification = laConnexion.createStatement();

			PreparedStatement requete = laConnexion.prepareStatement(
					"Update Ligne_commande set quantite=?, tarif_unitaire=? where id_commande=? and id_produit=?");
			requete.setInt(1, quantite);
			requete.setFloat(2, tarifunitaire);
			requete.setInt(3, idcommande);
			requete.setInt(4, idproduit);
			int res = requete.executeUpdate();
			System.out.println("Modification faite.");

		} catch (SQLException sqle) {
			System.out.println("Probl�me modification " + sqle.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<MySQLLigneCommandeDAO> LigneCommande() {
		@SuppressWarnings("rawtypes")
		ArrayList<MySQLLigneCommandeDAO> p = new ArrayList();
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("select * from Ligne_commande");
			ResultSet res = requete.executeQuery();

			while (res.next()) {
				p.addAll((Collection<? extends MySQLLigneCommandeDAO>) new LigneCommande(res.getInt(1), res.getInt(2),
						res.getInt(3), res.getFloat(4)));
			}

		} catch (SQLException sqle) {
			System.out.println("Probl�me ArrayList " + sqle.getMessage());
		}
		return p;

	}
}
