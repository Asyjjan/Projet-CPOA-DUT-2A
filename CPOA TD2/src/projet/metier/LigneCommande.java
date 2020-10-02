package projet.metier;

public class LigneCommande {

	private int quantite;
	private float tarif;

	public LigneCommande(int quantite, float tarif) {
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

	public float getTarif() {
		return tarif;
	}

	public void setTarif(float tarif) {
		this.tarif = tarif;
	}

	@Override
	public String toString() {
		return "LigneCommande [idcommande=" + ", quantite=" + quantite
				+ ", tarif=" + tarif + "]";
	}


}
