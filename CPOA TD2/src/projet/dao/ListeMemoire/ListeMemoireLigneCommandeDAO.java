package projet.dao.ListeMemoire;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import projet.dao.modele.LigneCommandeDAO;
import projet.metier.*;

public class ListeMemoireLigneCommandeDAO implements LigneCommandeDAO {

	private static ListeMemoireLigneCommandeDAO instance;

	private List<LigneCommande> donnees;

	public static ListeMemoireLigneCommandeDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireLigneCommandeDAO();
		}

		return instance;
	}

	private ListeMemoireLigneCommandeDAO() {

		this.donnees = new ArrayList<LigneCommande>();

		this.donnees.add(new LigneCommande(2, 41));
		this.donnees.add(new LigneCommande(1, 15));
		this.donnees.add(new LigneCommande(4, 35));
	}

	@Override
	public ArrayList<LigneCommande> findAll() {
		return (ArrayList<LigneCommande>) this.donnees;
	}

	@Override
	public boolean create(int idc, int idp, LigneCommande objet) throws SQLException {
		objet.setIdcommande(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setIdcommande(objet.getIdcommande() + 1);
		}
		boolean ok = this.donnees.add(objet);

		return ok;
	}

	@Override
	public boolean update(int idc, int idp, LigneCommande objet) throws SQLException {

		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une ligneCommande inexistante");
		} else {

			this.donnees.set(idx, objet);
		}

		return true;
	}

	@Override
	public boolean delete(int idc, int idp) throws SQLException {

		LigneCommande supprime;

		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une ligneCommande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}

		return objet.equals(supprime);
	}

	@Override
	public LigneCommande getById(int idc, int idp) throws SQLException {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new LigneCommande(1, 41));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune ligneCommande ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}
}
