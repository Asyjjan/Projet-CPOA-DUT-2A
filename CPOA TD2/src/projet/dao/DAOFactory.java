package projet.dao;

public abstract class DAOFactory {
	public static DAOFactory getDAOFactory(Persistance cible) {
		
		DAOFactory daoF = null;
		
		switch (cible) {
			case MYSQL:
				daoF = new MySQLDAOFactory();
				break;
			case LISTE_MEMOIRE:
				daoF = new ListeMemoireDAOFactory();
				break;
			}
		
		return daoF;
	}
	
	public abstract CategorieDAO getCategorieDAO();
	public abstract ClientDAO getClientDAO();
	public abstract CommandeDAO getCommandeDAO();
	public abstract LigneCommandeDAO getLigneCommandeDAO();
	public abstract ProduitDAO getProduitDAO();
}
