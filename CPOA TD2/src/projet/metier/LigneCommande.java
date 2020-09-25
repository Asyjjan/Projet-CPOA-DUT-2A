package projet.metier;

public class LigneCommande {

	private int idcommande;
	private int idproduit;
	private int quantite;
	private float tarif;
	
	public LigneCommande(int idcommande, int idproduit, int quantite, float tarif) {
		super();
		this.idcommande = idcommande;
		this.idproduit = idproduit;
		this.quantite = quantite;
		this.tarif = tarif;
	}

	public int getIdcommande() {
		return idcommande;
	}

	public void setIdcommande(int idcommande) {
		this.idcommande = idcommande;
	}

	public int getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public float getTarif() {
		return tarif;
	}

	public void setTarif(float tarif) {
		this.tarif = tarif;
	}
	

}
