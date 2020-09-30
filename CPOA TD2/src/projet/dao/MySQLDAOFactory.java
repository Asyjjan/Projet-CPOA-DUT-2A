package projet.dao;

import projet.dao.modele.CategorieDAO;
import projet.dao.modele.ClientDAO;
import projet.dao.modele.CommandeDAO;
import projet.dao.modele.LigneCommandeDAO;
import projet.dao.modele.ProduitDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public CategorieDAO getCategorieDAO() {
		// TODO Auto-generated method stub
		return projet.dao.MYSQLDAO.MySQLCategorieDAO.getInstance();
	}

	@Override
	public ClientDAO getClientDAO() {
		// TODO Auto-generated method stub
		return projet.dao.MYSQLDAO.MySQLClientDAO.getInstance();
	}

	@Override
	public CommandeDAO getCommandeDAO() {
		// TODO Auto-generated method stub
		return projet.dao.MYSQLDAO.MySQLCommandeDAO.getInstance();
	}

	@Override
	public LigneCommandeDAO getLigneCommandeDAO() {
		// TODO Auto-generated method stub
		return projet.dao.MYSQLDAO.MySQLLigneCommandeDAO.getInstance();
	}

	@Override
	public ProduitDAO getProduitDAO() {
		// TODO Auto-generated method stub
		return projet.dao.MYSQLDAO.MySQLProduitDAO.getInstance();
	}

}
