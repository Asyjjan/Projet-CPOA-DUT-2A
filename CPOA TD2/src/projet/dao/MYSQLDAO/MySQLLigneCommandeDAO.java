package projet.dao.MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import projet.dao.modele.LigneCommandeDAO;
import projet.menu.Connexion;
import projet.metier.*;

public class MySQLLigneCommandeDAO implements LigneCommandeDAO{

	private static MySQLLigneCommandeDAO instance;

	private MySQLLigneCommandeDAO() {
	}

	public static MySQLLigneCommandeDAO getInstance() {
		if (instance == null) {
			instance = new MySQLLigneCommandeDAO();
		}
		return instance;
	}

	@Override
	public boolean create(int idcommande, LigneCommande objet) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"INSERT INTO 	Ligne_commande (id_commande, id_produit, quantite, tarif_unitaire) VALUES (?,?,?,?)");
		requete.setInt(1, idcommande);
		requete.setInt(2, objet.getIdprod());
		requete.setInt(3, objet.getQuantite());
		requete.setFloat(4, objet.getTarif());
		nbLignes = requete.executeUpdate();
		return nbLignes == 1;
	}

	@Override
	public boolean update(int idcommande, int idproduit, LigneCommande objet) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"UPDATE Ligne_commande SET quantite = ?, tarif_unitaire = ?, WHERE id_commande =" + idcommande + "and id_produit ="+ idproduit + "VALUES(?,?)");
		requete.setInt(1, objet.getQuantite());
		requete.setFloat(2, objet.getTarif());
		nbLignes = requete.executeUpdate();
		return nbLignes == 1;
	}

	@Override
	public boolean delete(int idcommande, int idproduit) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Ligne_commande WHERE id_commande =" +idcommande +" and id_produit =" +idproduit);
		nbLignes = requete.executeUpdate();
		return nbLignes == 1;
	}

	@Override
	public LigneCommande getById(int idprod, int idcommande, int idproduit) throws SQLException {
		int quantite = 0;
		float tarif = 0;
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		ResultSet res = requete.executeQuery("SELECT * FROM Ligne_commande WHERE id_commande = " + idcommande + " and id_produit =" +idproduit);
		if (res.next()) {
			quantite = res.getInt(3);
			tarif = res.getFloat(4);
		}
		LigneCommande lignecommande = new LigneCommande(idprod, quantite, tarif);
		return lignecommande;
	}
	
	@Override
    public ArrayList<LigneCommande> findAll() throws SQLException{
        ArrayList<LigneCommande> listeLigneCommande = new ArrayList<LigneCommande>();;
        
        Connection laConnexion = Connexion.creeConnexion();
      
        PreparedStatement requete = laConnexion.prepareStatement("select * from `Ligne_commande`");
        ResultSet res = requete.executeQuery();
  
        while (res.next()) { 
            listeLigneCommande.add(new LigneCommande(res.getInt(2), res.getInt(3), res.getFloat(4))) ;
        }
  
        if (laConnexion != null) 
            laConnexion.close();
         
        return listeLigneCommande;
    }

	}
