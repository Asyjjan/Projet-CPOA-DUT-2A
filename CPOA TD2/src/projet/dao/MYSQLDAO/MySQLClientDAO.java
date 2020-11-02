package projet.dao.MYSQLDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import projet.dao.modele.ClientDAO;
import projet.metier.Client;
import projet.utilitaire.Connexion;

public class MySQLClientDAO implements ClientDAO {

	private static MySQLClientDAO instance;

	private MySQLClientDAO() {
	}

	public static MySQLClientDAO getInstance() {
		if (instance == null) {
			instance = new MySQLClientDAO();
		}
		return instance;
	}

	@Override
	public boolean create(Client objet) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"INSERT INTO 	Client (nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays) VALUES (?,?,?,?,?,?,?,?,?)");
		requete.setString(1, objet.getNom());
		requete.setString(2, objet.getPrenom());
		requete.setString(3, objet.getIdentifiant());
		requete.setString(4, objet.getMdp());
		requete.setString(5, objet.getAdrnumero());
		requete.setString(6, objet.getAdrvoie());
		requete.setString(7, objet.getAdrcodepostal());
		requete.setString(8, objet.getAdrville());
		requete.setString(9, objet.getAdrpays());
		nbLignes = requete.executeUpdate();
		return nbLignes == 1;
	}

	@Override
	public boolean update(Client objet) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement(
				"UPDATE Client SET nom = ?, prenom = ?, identifiant = ?, mot_de_passe = ?, adr_numero = ?, adr_voie = ?, adr_code_postal = ?, adr_ville = ?, adr_pays = ? WHERE Client.id_client = ?");
		requete.setString(1, objet.getNom());
		requete.setString(2, objet.getPrenom());
		requete.setString(3, objet.getIdentifiant());
		requete.setString(4, objet.getMdp());
		requete.setString(5, objet.getAdrnumero());
		requete.setString(6, objet.getAdrvoie());
		requete.setString(7, objet.getAdrcodepostal());
		requete.setString(8, objet.getAdrville());
		requete.setString(9, objet.getAdrpays());
		requete.setInt(10, objet.getIdclient());
		nbLignes = requete.executeUpdate();
		return nbLignes == 1;
	}

	@Override
	public boolean delete(Client objet) throws SQLException {
		int nbLignes = 0;
		Connection laConnexion = Connexion.creeConnexion();
		PreparedStatement requete = laConnexion.prepareStatement("DELETE FROM Client WHERE Client.id_client = ?");
		requete.setInt(1, objet.getIdclient());
		nbLignes = requete.executeUpdate();
		return nbLignes == 1;
	}

	@Override
	public Client getById(int id) throws SQLException {
		String nom = "";
		String prenom = "";
		String identifiant = "";
		String mdp = "";
		String adrNum = "";
		String adrVoie = "";
		String adrCP = "";
		String adrVille = "";
		String adrPays = "";
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		ResultSet res = requete.executeQuery("SELECT * FROM Client WHERE Client.id_client =" + id);
		if (res.next()) {
			nom = res.getString(2);
			prenom = res.getString(3);
			identifiant = res.getString(4);
			mdp = res.getString(5);
			adrNum = res.getString(6);
			adrVoie = res.getString(7);
			adrCP = res.getString(8);
			adrVille = res.getString(9);
			adrPays = res.getString(10);
		}
		Client client = new Client(id, nom, prenom, identifiant, mdp, adrNum, adrVoie, adrCP, adrVille, adrPays);
		return client;
	}

	@Override
	public ArrayList<Client> findAll() throws SQLException {
		ArrayList<Client> liste = new ArrayList<Client>();
		Connection laConnexion = Connexion.creeConnexion();
		Statement requete = laConnexion.createStatement();
		ResultSet res = requete.executeQuery("SELECT * FROM Client ORDER BY nom ASC");
		while (res.next()) {
			int idC = res.getInt(1);
			String nom = res.getString(2);
			String prenom = res.getString(3);
			String identifiant = res.getString(4);
			String mdp = res.getString(5);
			String adrNum = res.getString(6);
			String adrVoie = res.getString(7);
			String adrCP = res.getString(8);
			String adrVille = res.getString(9);
			String adrPays = res.getString(10);
			Client client = new Client(idC, nom, prenom, identifiant, mdp, adrNum, adrVoie, adrCP, adrVille, adrPays);
			liste.add(client);
		}
		return liste;
	}

}