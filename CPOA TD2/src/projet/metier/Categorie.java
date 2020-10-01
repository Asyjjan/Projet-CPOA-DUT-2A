package projet.metier;

public class Categorie {

	private int idcateg;
	private String titre;
	private String visuel;

	public Categorie(int idcateg, String titre, String visuel) {
		super();
		this.idcateg = idcateg;
		this.titre = titre;
		this.visuel = visuel;
	}

	public int getIdcateg() {
		return idcateg;
	}

	public void setIdcateg(int idcateg) {
		this.idcateg = idcateg;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getVisuel() {
		return visuel;
	}

	public void setVisuel(String visuel) {
		this.visuel = visuel;
	}

	@Override
	public String toString() {
		return "Categorie [idcateg=" + idcateg + ", titre=" + titre + ", visuel=" + visuel + "]";
	}




}
