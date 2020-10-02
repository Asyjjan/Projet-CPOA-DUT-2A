package projet.dao.MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import projet.menu.Connexion;
import projet.metier.*;

public class MySQLProduitDAO {
	
	public static void create(int idproduit, String nom, String description, float tarif, String visuel, int idcateg) {
        
        try {
            Connection laConnexion = Connexion.creeConnexion();
            Statement insertion = laConnexion.createStatement();
            
                PreparedStatement requete = laConnexion.prepareStatement("insert into Produit (id_produit, nom, description, tarif, visuel, id_categorie) values (?,?,?,?,?,?)");
				requete.setInt(1, idproduit);
				requete.setString(2, nom);
				requete.setString(3, description);
				requete.setFloat(4, tarif);
				requete.setString(5, visuel);
				requete.setInt(6, idcateg);
				int resu = requete.executeUpdate();
				System.out.println("Ins�ration faite.");
				
        }catch (SQLException sqle) {
					System.out.println("Probl�me insert " + sqle.getMessage());
					 			   }
    
}

	public static void delete(int idproduit) throws SQLException {
		try {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("delete from Produit where id_produit=?");
				requete.setInt(1, idproduit);
				int res = requete.executeUpdate();
				System.out.println("Suppresion faite.");
		}catch (SQLException sqle) {
			System.out.println("Probl�me delete " + sqle.getMessage());
								   }
		}
	
	public static void update(int idproduit, String nom, String description, float tarif, String visuel, int idcateg) {
        
        try {
            Connection laConnexion = Connexion.creeConnexion();
            Statement modification = laConnexion.createStatement();
            
                PreparedStatement requete = laConnexion.prepareStatement("Update Produit set nom=?, description=?, tarif=?, visuel=?, id_categorie=? where id_produit=?");
				requete.setString(1,nom);
				requete.setString(2,description);	
				requete.setFloat(3, tarif);
				requete.setString(4, visuel);
				requete.setInt(5, idcateg);	
				requete.setInt(6, idproduit);
				int resu = requete.executeUpdate();
				System.out.println("Modification faite.");
				
        }catch (SQLException sqle) {
			System.out.println("Probl�me modification " + sqle.getMessage());
		   }
    }
	
	@SuppressWarnings("unchecked")
	public static ArrayList<MySQLProduitDAO> Produit(){
		@SuppressWarnings("rawtypes")
		ArrayList<MySQLProduitDAO> p= new ArrayList();
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("select * from Produit");
				ResultSet res = requete.executeQuery();
				
				while (res.next()) {
					p.add(new Produit (res.getInt(1),res.getString(2),res.getString(3),res.getFloat(4),res.getString(5),res.getInt(6)));
				}
				
		}catch (SQLException sqle) {
			System.out.println("Probl�me ArrayList " + sqle.getMessage());
		   }
		return p;
		
	}
	
	public static Produit getById(int id) throws SQLException {
		Produit produit;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("select * from `Categorie` where id_categorie=" + id);
					ResultSet res = requete.executeQuery();
					
					produit = new Produit(res.getInt(1), res.getString(2), res.getString(3),res.getFloat(4), res.getString(5), res.getInt(6));
					
					if (laConnexion != null)
						laConnexion.close();
		return produit;
	}

}
