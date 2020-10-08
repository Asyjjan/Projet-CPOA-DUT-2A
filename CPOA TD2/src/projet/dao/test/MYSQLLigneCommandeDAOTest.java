package projet.dao.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.LigneCommande;

public class MYSQLLigneCommandeDAOTest {

	public void testCreateCasGeneral() throws SQLException {
		LigneCommande c = new LigneCommande(9, 40);
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertTrue(daos.getLigneCommandeDAO().create(1,1,c));
	}

	@Test
	public void testCreateExistant() throws SQLException {
		LigneCommande c = new LigneCommande(1, 15);
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertFalse(daos.getLigneCommandeDAO().create(1,1,c));
	}

	@Test
	public void testUpdateCasGeneral() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		LigneCommande c = (LigneCommande) daos.getLigneCommandeDAO().getById(1,1);
		c.setTarif(40);
		assertTrue(daos.getLigneCommandeDAO().update(1,1,c));
	}

	@Test
	public void testDeleteCasGeneral() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		LigneCommande c = (LigneCommande) daos.getLigneCommandeDAO().getById(9, 0);
		assertTrue(daos.getLigneCommandeDAO().delete(1,1));
	}

	@Test
	public void testDeleteInexistant() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertFalse(daos.getLigneCommandeDAO().delete(1,1));
	}

	@Test
	public void testUpdateInexistant() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertFalse(daos.getLigneCommandeDAO().update(0, 0, daos.getLigneCommandeDAO().getById(11,1)));
	}

	@Test
	public void getById() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		int i = ((LigneCommande) daos.getLigneCommandeDAO().getById(1,1)).getQuantite();
		assertEquals(i, 1);
	}

	@Test
	public void getByIdInexistant() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertEquals(daos.getLigneCommandeDAO().getById(12,1), null);
	}
}
