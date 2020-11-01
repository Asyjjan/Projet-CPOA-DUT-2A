package projet.metier;

public class LigneCommande {
	
	private int idcom;
	private int idprod;
	private int quantite;
	private float tarif;

	public LigneCommande(int idcom, int idprod, int quantite, float tarif) {
		super();
		this.idcom = idcom;
		this.idprod = idprod;
		this.quantite = quantite;
		this.tarif = tarif;
	}

	public int getIdcom() {
		return idcom;
	}

	public void setIdcom(int idcom) {
		this.idcom = idcom;
	}
	
	public int getIdprod() {
		return idprod;
	}

	public void setIdProd(int idprod) {
		this.idprod = idprod;
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
