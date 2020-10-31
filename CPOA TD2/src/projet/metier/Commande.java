package projet.metier;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import projet.dao.DAOFactory;
import projet.dao.Persistance;

public class Commande {
	DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private int idcommande;
	private LocalDate datecommande;
	private int idclient;
	private HashMap<Produit, LigneCommande> ligneCommande;

	public Commande(int idcommande, LocalDate datecommande, int idclient,
			HashMap<Produit, LigneCommande> ligneCommande) {
		super();
		this.idcommande = idcommande;
		this.datecommande = datecommande;
		this.idclient = idclient;
		this.ligneCommande = ligneCommande;
	}


	public int getIdcommande() {
		return idcommande;
	}



	public void setIdcommande(int idcommande) {
		if(idcommande >0)
			this.idcommande = idcommande;
	}



	public LocalDate getDatecommande() {
		return datecommande;
	}



	public void setDatecommande(LocalDate datecommande) {
		this.datecommande = datecommande;
	}



	public int getIdclient() {
		return idclient;
	}



	public void setIdclient(int idclient) {
		if(idclient >0)
			this.idclient = idclient;
	}



	public HashMap<Produit, LigneCommande> getLigneCommande() {
		return ligneCommande;
	}



	public void setLigneCommande(HashMap<Produit, LigneCommande> ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		if (idcommande != other.idcommande)
			return false;
		return true;
	}


	public void ajouteLigneCom(Produit prod, LigneCommande LC) {
		ligneCommande.put(prod, LC);
	}

	public void supprimeLigneCom(Produit prod) {
		ligneCommande.remove(prod);
	}

	public void modifieLigneCom(Produit prod, LigneCommande LC) {
		ligneCommande.replace(prod, LC);
	}

	@Override
	public String toString() {
		return "Commande numéro : " + idcommande + " , " + formatage.format(getDatecommande());
	}
}
