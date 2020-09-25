package projet.dao.MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class MySQLCommandeDAO {
		
		public static void create(int id_commande,LocalDate datecommande,int id_client) {
	        
	        try {
	            Connection laConnexion = Connexion.creeConnexion();
	            Statement insertion = laConnexion.createStatement();
	            
	                PreparedStatement requete = laConnexion.prepareStatement("insert into Commande (id_commande, date_commande, id_client) values (?,?,?)");
					requete.setInt(1, id_commande);
					requete.setDate(2, datecommande);
					requete.setInt(3, id_client);
					int resu = requete.executeUpdate();
					System.out.println("Ins�ration faite.");
					
	        }catch (SQLException sqle) {
						System.out.println("Probl�me insert " + sqle.getMessage());
						 			   }
	    
	}

		public static void Delete(int idcommande) throws SQLException {
			try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("delete from Commande where id_commande=?");
					requete.setInt(1, idcommande);
					int res = requete.executeUpdate();
					System.out.println("Suppresion faite.");
			}catch (SQLException sqle) {
				System.out.println("Probl�me delete " + sqle.getMessage());
									   }
			}
		
		public static void update(int idcommande, LocalDate datecommande, int idclient) {
	        
	        try {
	            Connection laConnexion = Connexion.creeConnexion();
	            Statement modification = laConnexion.createStatement();
	            
	                PreparedStatement requete = laConnexion.prepareStatement("Update Commande set date_commande=?, id_client=? where id_commande=?");
					requete.setDate(1,datecommande);
					requete.setInt(2,idclient);	
					requete.setInt(3, idcommande);
					int resu = requete.executeUpdate();
					System.out.println("Modification faite.");
					
	        }catch (SQLException sqle) {
				System.out.println("Probl�me modification " + sqle.getMessage());
			   }
	    }
		
		@SuppressWarnings("unchecked")
		public static ArrayList<MySQLCommandeDAO> Commande(){
			@SuppressWarnings("rawtypes")
			ArrayList<MySQLCommandeDAO> p= new ArrayList();
			try {
				Connection laConnexion = Connexion.creeConnexion();
				PreparedStatement requete = laConnexion.prepareStatement("select * from Commande");
					ResultSet res = requete.executeQuery();
					
					while (res.next()) {
						p.add(new Commande (res.getDate(1),res.getInt(2),res.getInt(3)));
					}
					
			}catch (SQLException sqle) {
				System.out.println("Probl�me ArrayList " + sqle.getMessage());
			   }
			return p;
			
		}
}
