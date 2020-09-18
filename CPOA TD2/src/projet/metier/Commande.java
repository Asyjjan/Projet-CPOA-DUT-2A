package projet.metier;

import java.time.LocalDate;
import java.util.HashMap;

public class Commande {

	private int idcmd;
	private LocalDate datecmd;
	private int idclient;
	private HashMap<Produit, LigneCommande> ligneCommande;
	
	public Commande(int idcmd, LocalDate datecmd, int idclient) {
		super();
		this.idcmd = idcmd;
		this.datecmd = datecmd;
		this.idclient = idclient;
	}

	public int getIdcmd() {
		return idcmd;
	}

	public void setIdcmd(int idcmd) {
		this.idcmd = idcmd;
	}

	public LocalDate getDatecmd() {
		return datecmd;
	}

	public void setDatecmd(LocalDate datecmd) {
		this.datecmd = datecmd;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
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
