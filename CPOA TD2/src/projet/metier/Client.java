package projet.metier;

public class Client {

	private int idclient;
	private String nom;
	private String prenom;
	private String identifiant;
	private String mdp;
	private String adrnumero;
	private String adrvoie;
	private String adrcodepostal;
	private String adrville;
	private String adrpays;

	public Client(int idclient, String nom, String prenom, String identifiant, String mdp, String adrnumero,
			String adrvoie, String adrcodepostal, String adrville, String adrpays) {
		super();
		this.idclient = idclient;
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
		this.mdp = mdp;
		this.adrnumero = adrnumero;
		this.adrvoie = adrvoie;
		this.adrcodepostal = adrcodepostal;
		this.adrville = adrville;
		this.adrpays = adrpays;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		if (this.prenom == null || this.prenom.trim().isEmpty()) {

		} else {
			this.prenom = prenom;
		}
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		if (this.identifiant == null || this.identifiant.trim().isEmpty()) {

		} else {
			this.identifiant = identifiant;
		}
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		if (this.mdp == null || this.mdp.trim().isEmpty()) {

		} else {
			this.mdp = mdp;
		}
	}

	public String getAdrnumero() {
		return adrnumero;
	}

	public void setAdrnumero(String adrnumero) {
		if (this.adrnumero == null || this.adrnumero.trim().isEmpty()) {

		} else {
			this.adrnumero = adrnumero;
		}
	}

	public String getAdrvoie() {
		return adrvoie;
	}

	public void setAdrvoie(String adrvoie) {
		if (this.adrvoie == null || this.adrvoie.trim().isEmpty()) {

		} else {
			this.adrvoie = adrvoie;
		}
	}

	public String getAdrcodepostal() {
		return adrcodepostal;
	}

	public void setAdrcodepostal(String adrcodepostal) {
		if (this.adrcodepostal == null || this.adrcodepostal.trim().isEmpty()) {

		} else {
			this.adrcodepostal = adrcodepostal;
		}
	}

	public String getAdrville() {
		return adrville;
	}

	public void setAdrville(String adrville) {
		if (this.adrville == null || this.adrville.trim().isEmpty()) {

		} else {
			this.adrville = adrville;
		}
	}

	public String getAdrpays() {
		return adrpays;
	}

	public void setAdrpays(String adrpays) {
		if (this.adrpays == null || this.adrpays.trim().isEmpty()) {

		} else {
			this.adrpays = adrpays;
		}
	}

	@Override
	public String toString() {
		return "Client [idclient=" + idclient + ", nom=" + nom + ", prenom=" + prenom + ", identifiant=" + identifiant
				+ ", mdp=" + mdp + ", adrnumero=" + adrnumero + ", adrvoie=" + adrvoie + ", adrcodepostal="
				+ adrcodepostal + ", adrville=" + adrville + ", adrpays=" + adrpays + "]";
	}

}
