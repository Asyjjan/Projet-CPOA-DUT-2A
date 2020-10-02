package projet.dao;
import projet.dao.modele.*;
import projet.dao.ListeMemoire.ListeMemoireCategorieDAO;
import projet.dao.ListeMemoire.ListeMemoireClientDAO;
import projet.dao.ListeMemoire.ListeMemoireCommandeDAO;
import projet.dao.ListeMemoire.ListeMemoireLigneCommandeDAO;
import projet.dao.ListeMemoire.ListeMemoireProduitDAO;
import projet.dao.modele.CategorieDAO;
import projet.dao.modele.ClientDAO;
import projet.dao.modele.CommandeDAO;
import projet.dao.modele.LigneCommandeDAO;
import projet.dao.modele.ProduitDAO;

public class ListeMemoireDAOFactory extends DAOFactory {

	@Override
	public CategorieDAO getCategorieDAO() {
		// TODO Auto-generated method stub
		return ListeMemoireCategorieDAO.getInstance();
	}

	@Override
	public ClientDAO getClientDAO() {
		// TODO Auto-generated method stub
		return ListeMemoireClientDAO.getInstance();
	}

	@Override
	public CommandeDAO getCommandeDAO() {
		// TODO Auto-generated method stub
		return ListeMemoireCommandeDAO.getInstance();
	}

	@Override
	public LigneCommandeDAO getLigneCommandeDAO() {
		// TODO Auto-generated method stub
		return ListeMemoireLigneCommandeDAO.getInstance();
	}

	@Override
	public ProduitDAO getProduitDAO() {
		// TODO Auto-generated method stub
		return ListeMemoireProduitDAO.getInstance();
	}

}
