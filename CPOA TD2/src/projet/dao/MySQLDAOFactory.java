package projet.dao;

import projet.dao.modele.*;
import projet.dao.MYSQLDAO.MySQLCategorieDAO;
import projet.dao.MYSQLDAO.MySQLClientDAO;
import projet.dao.MYSQLDAO.MySQLCommandeDAO;
import projet.dao.MYSQLDAO.MySQLLigneCommandeDAO;
import projet.dao.MYSQLDAO.MySQLProduitDAO;
import projet.dao.modele.CategorieDAO;
import projet.dao.modele.ClientDAO;
import projet.dao.modele.CommandeDAO;
import projet.dao.modele.LigneCommandeDAO;
import projet.dao.modele.ProduitDAO;

public class MySQLDAOFactory extends DAOFactory {

	@Override
	public CategorieDAO getCategorieDAO() {
		// TODO Auto-generated method stub
		return (CategorieDAO) MySQLCategorieDAO.getInstance();
	}

	@Override
	public ClientDAO getClientDAO() {
		// TODO Auto-generated method stub
		return (ClientDAO) MySQLClientDAO.getInstance();
	}

	@Override
	public CommandeDAO getCommandeDAO() {
		// TODO Auto-generated method stub
		return (CommandeDAO) MySQLCommandeDAO.getInstance();
	}

	@Override
	public LigneCommandeDAO getLigneCommandeDAO() {
		// TODO Auto-generated method stub
		return (LigneCommandeDAO) MySQLLigneCommandeDAO.getInstance();
	}

	@Override
	public ProduitDAO getProduitDAO() {
		// TODO Auto-generated method stub
		return (ProduitDAO) MySQLProduitDAO.getInstance();
	}

}
