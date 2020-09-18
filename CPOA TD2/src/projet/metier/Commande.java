package projet.metier;

import java.time.LocalDate;
import java.util.HashMap;

public class Commande {

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
		this.idclient = idclient;
	}



	public HashMap<Produit, LigneCommande> getLigneCommande() {
		return ligneCommande;
	}



	public void setLigneCommande(HashMap<Produit, LigneCommande> ligneCommande) {
		this.ligneCommande = ligneCommande;
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
		
}
