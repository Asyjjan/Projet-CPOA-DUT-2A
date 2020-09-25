package projet.dao.MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLLigneCommandeDAO {
	
public static void create(int idcommande, int idproduit, int quantite,float tarifunitaire) {
        
        try {
            Connection laConnexion = Connexion.creeConnexion();
            Statement insertion = laConnexion.createStatement();
            
                PreparedStatement requete = laConnexion.prepareStatement("insert into Ligne_commande (id_commande,id_produit,quantite,tarif_unitaire) values (?,?,?,?)");
				requete.setInt(1, idcommande);
				requete.setInt(2, idproduit);
				requete.setInt(3, quantite);
				requete.setFloat(4, tarifunitaire);
				int resu = requete.executeUpdate();
				System.out.println("Ins�ration faite.");
				
        }catch (SQLException sqle) {
					System.out.println("Probl�me insert " + sqle.getMessage());
					 			   }
    
}

	public static void delete(int idcommande ,int idproduit) throws SQLException {
		try {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("delete from Ligne_commande where id_commande=? and id_produit=?");
				requete.setInt(1, idcommande);
				requete.setInt(2, idproduit);
				int res = requete.executeUpdate();
				System.out.println("Suppresion faite.");
		}catch (SQLException sqle) {
			System.out.println("Probl�me delete " + sqle.getMessage());
								   }
		}
	
	public static void update(int idcommande, int idproduit,int quantite, float tarifunitaire) {
        
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
}
