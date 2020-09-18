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
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getAdrnumero() {
		return adrnumero;
	}

	public void setAdrnumero(String adrnumero) {
		this.adrnumero = adrnumero;
	}

	public String getAdrvoie() {
		return adrvoie;
	}

	public void setAdrvoie(String adrvoie) {
		this.adrvoie = adrvoie;
	}

	public String getAdrcodepostal() {
		return adrcodepostal;
	}

	public void setAdrcodepostal(String adrcodepostal) {
		this.adrcodepostal = adrcodepostal;
	}

	public String getAdrville() {
		return adrville;
	}

	public void setAdrville(String adrville) {
		this.adrville = adrville;
	}

	public String getAdrpays() {
		return adrpays;
	}

	public void setAdrpays(String adrpays) {
		this.adrpays = adrpays;
	}
	
	
	
	
	
}
