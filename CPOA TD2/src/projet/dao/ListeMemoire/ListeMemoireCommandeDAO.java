package projet.dao.ListeMemoire;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projet.dao.modele.CommandeDAO;
import projet.metier.Commande;
import projet.metier.LigneCommande;
import projet.metier.Produit;

public class ListeMemoireCommandeDAO implements CommandeDAO {

	private static ListeMemoireCommandeDAO instance;

	private List<Commande> donnees;


	public static ListeMemoireCommandeDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireCommandeDAO();
		}

		return instance;
	}

	private ListeMemoireCommandeDAO() {

		this.donnees = new ArrayList<Commande>();

		HashMap lignecommande<new Produit(), 
		this.donnees.add(new Commande(1, "2020-09-02 13:12:00", 1, ligneCommande));
		this.donnees.add(new Commande(2, "2020-08-30 11:22:00", 1, ligneCommande));
	}


	@Override
	public boolean create(Commande objet) {

		objet.setIdcommande(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setIdcommande(objet.getIdcommande() + 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(Commande objet) {
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une commande inexistante");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}

	@Override
	public boolean delete(Commande objet) {

		Commande supprime;
		
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une commande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public Commande getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Commande());
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun client ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Commande> findAll() {
		return (ArrayList<Commande>) this.donnees;
	}
}