package projet.dao.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.LigneCommande;

public class ListeMemoireLigneCommandeDAOTest {

	@Test
	public void testCreateCasGeneral() throws SQLException {
		LigneCommande c = new LigneCommande(7, 20);
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertTrue(daos.getLigneCommandeDAO().create(1,1,c));
	}

	@Test
	public void testCreateExistant() throws SQLException {
		LigneCommande c = new LigneCommande(1, 15);
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getLigneCommandeDAO().create(1,1,c));
	}

	@Test
	public void testUpdateCasGeneral() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		LigneCommande c = (LigneCommande) daos.getLigneCommandeDAO().getById(1,1);
		c.setTarif(20);
		assertTrue(daos.getLigneCommandeDAO().update(1,1,c));
	}

	@Test
	public void testDeleteCasGeneral() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		LigneCommande c = (LigneCommande) daos.getLigneCommandeDAO().getById(1, 1);
		assertTrue(daos.getLigneCommandeDAO().delete(1,1));
	}

	@Test
	public void testDeleteInexistant() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getLigneCommandeDAO().delete(1,1));
	}

	@Test
	public void testUpdateInexistant() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getLigneCommandeDAO().update(1, 1, daos.getLigneCommandeDAO().getById(1,1)));
	}

	@Test
	public void getById() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		int i = ((LigneCommande) daos.getLigneCommandeDAO().getById(1,1)).getQuantite();
		assertEquals(i, 1);
	}

	@Test
	public void getByIdInexistant() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertEquals(daos.getLigneCommandeDAO().getById(1,1), null);
	}
}
