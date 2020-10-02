package projet.dao.ListeMemoire;

import java.util.ArrayList;
import java.util.List;

import projet.dao.modele.ProduitDAO;
import projet.metier.Produit;

public class ListeMemoireProduitDAO implements ProduitDAO {

	private static ListeMemoireProduitDAO instance;

	private List<Produit> donnees;


	public static ListeMemoireProduitDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireProduitDAO();
		}

		return instance;
	}

	private ListeMemoireProduitDAO() {

		this.donnees = new ArrayList<Produit>();

		this.donnees.add(new Produit(2, "Sonic te kiffe", "InspirÈ par la saga SÈga (c'est plus fort que toi !), un pull 100% gamer qui te permettra de faire baver d'envie tes petits camarades de jeu.", (float)41.5, "pull1.png", 1));
		this.donnees.add(new Produit(6, "La chaleur des rennes", "Classique mais efficace, un bonnet dont l'ÈlÈgance", 15, "bonnet0.png", 2));
	}


	@Override
	public boolean create(Produit objet) {

		objet.setIdprod(3);
		// Ne fonctionne que si l'objet m√©tier est bien fait...
		while (this.donnees.contains(objet)) {

			objet.setIdprod(objet.getIdprod() + 1);
		}
		boolean ok = this.donnees.add(objet);

		return ok;
	}

	@Override
	public boolean update(Produit objet) {

		// Ne fonctionne que si l'objet m√©tier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un produit inexistant");
		} else {

			this.donnees.set(idx, objet);
		}

		return true;
	}

	@Override
	public boolean delete(Produit objet) {

		Produit supprime;

		// Ne fonctionne que si l'objet m√©tier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un produit inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}

		return objet.equals(supprime);
	}

	@Override
	public Produit getById(int id) {
		// Ne fonctionne que si l'objet m√©tier est bien fait...
		int idx = this.donnees.indexOf(new Produit(2, "Sonic te kiffe", "InspirÈ par la saga SÈga (c'est plus fort que toi !), un pull 100% gamer qui te permettra de faire baver d'envie tes petits camarades de jeu.", (float) 41.5, "pull1.png", 1));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucun client ne poss√®de cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	@Override
	public ArrayList<Produit> findAll() {
		return (ArrayList<Produit>) this.donnees;
	}
}

