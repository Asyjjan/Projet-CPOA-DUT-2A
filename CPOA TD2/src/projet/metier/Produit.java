package projet.metier;

public class Produit {

	private int idprod;
	private String nom;
	private String desc;
	private float tarif;
	private String visuel;
	private int idcateg;

	public Produit(int idprod, String nom, String desc, float tarif, String visuel, int idcateg) {
		super();
		this.idprod = idprod;
		this.nom = nom;
		this.desc = desc;
		this.tarif = tarif;
		this.visuel = visuel;
		this.idcateg = idcateg;
	}

	public int getIdprod() {
		return idprod;
	}

	public void setIdprod(int idprod) {
		this.idprod = idprod;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		if (this.nom == null || this.nom.trim().isEmpty()) {

		} else {
			this.nom = nom;
		}
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		if (this.desc == null || this.desc.trim().isEmpty()) {

		} else {
			this.desc = desc;
		}
	}

	public float getTarif() {
		return tarif;
	}

	public void setTarif(float tarif) {
		this.tarif = tarif;
	}

	public String getVisuel() {
		return visuel;
	}

	public void setVisuel(String visuel) {
		if (this.visuel == null || this.visuel.trim().isEmpty()) {

		} else {
			this.visuel = visuel;
		}
	}

	public int getIdcateg() {
		return idcateg;
	}

	public void setIdcateg(int idcateg) {
		this.idcateg = idcateg;
	}

	@Override
	public String toString() {
		return "Produit [idprod=" + idprod + ", nom=" + nom + ", desc=" + desc + ", tarif=" + tarif + ", visuel="
				+ visuel + ", idcateg=" + idcateg + "]";
	}

}