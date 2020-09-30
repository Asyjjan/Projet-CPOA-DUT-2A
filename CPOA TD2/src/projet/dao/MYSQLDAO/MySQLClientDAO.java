package projet.dao.MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import projet.menu.Connexion;
import projet.metier.*;

public class MySQLClientDAO {
		
	private static MySQLClientDAO instance;

	private MySQLClientDAO() {
	}

	public static MySQLClientDAO getInstance() {
		if (instance == null) {
			instance = new MySQLClientDAO();
		}
		return instance;
	}
	
	public static void create(int idclient, String nom, String prenom) {
        
        try {
            Connection laConnexion = Connexion.creeConnexion();
            Statement insert = laConnexion.createStatement();
            
            	PreparedStatement requete = laConnexion.prepareStatement("insert into Client (id_client, nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays) values (?,?,?,?,?,?,?,?,?,?)");
				requete.setInt(1, idclient);
				requete.setString(2, nom);
				requete.setString(3, prenom);
				requete.setString(4, "*");
				requete.setString(5, "*");
				requete.setString(6, "*");
				requete.setString(7, "*");
				requete.setString(8, "*");
				requete.setString(9, "*");
				requete.setString(10, "*");
				int resu = requete.executeUpdate();
				System.out.println("Ins�ration faite.");
		
            }catch (SQLException sqle) {
					System.out.println("Probl�me insert " + sqle.getMessage());
					 			   }
    }
	
	public static void delete(int idclient) throws SQLException {
		
		try {
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("delete from Client where id_client=?");
				requete.setInt(1, idclient);
				int res = requete.executeUpdate();
				System.out.println("Suppresion faite.");
		}catch (SQLException sqle) {
			System.out.println("Probl�me delete " + sqle.getMessage());
								   }
		}
	
	public static void update(int idclient, String nom, String prenom) {
        
        try {
            Connection laConnexion = Connexion.creeConnexion();
            Statement modif = laConnexion.createStatement();
            
                PreparedStatement requete = laConnexion.prepareStatement("Update Client set nom=?,prenom=? where id_client=?");
				requete.setString(1,nom);
				requete.setString(2,prenom);	
				requete.setInt(3, idclient);
				int resu = requete.executeUpdate();
				System.out.println("Modification faite.");
			
		}catch (SQLException sqle) {
			System.out.println("Probl�me modification " + sqle.getMessage());
		   }
    }
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Client> Client(){
		@SuppressWarnings("rawtypes")
		ArrayList<Client> cl= new ArrayList();
		try {
			Connection laConnexion = Connexion.creeConnexion();
			PreparedStatement requete = laConnexion.prepareStatement("select * from Client");
				ResultSet res = requete.executeQuery();
				
				while (res.next()) {
					cl.add(new Client (res.getInt(1),res.getString(2),res.getString(3), null, null, null, null, null, null, null));
				}
				
		}catch (SQLException sqle) {
			System.out.println("Probl�me ArrayList " + sqle.getMessage());
		   }
		return cl;
		
	}
}
