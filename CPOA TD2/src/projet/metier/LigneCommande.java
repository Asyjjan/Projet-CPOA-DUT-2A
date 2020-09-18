package projet.metier;

public class LigneCommande {

	private int quantite;
	private double tarif;
	
	public LigneCommande(int quantite, double tarif) {
		super();
		this.quantite = quantite;
		this.tarif = tarif;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getTarif() {
		return tarif;
	}

	public void setTarif(double tarif) {
		this.tarif = tarif;
	}

}
