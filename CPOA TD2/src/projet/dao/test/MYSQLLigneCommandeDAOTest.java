package projet.dao.test;

import org.junit.Test;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.LigneCommande;

public class MYSQLLigneCommandeDAOTest {

	public void testCreateCasGeneral() {
		LigneCommande c = new LigneCommande(9, 40);
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertTrue(daos.getLigneCommandeDAO().create(c));
	}

	@Test
	public void testCreateExistant() {
		LigneCommande c = new LigneCommande(1, 15);
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertFalse(daos.getLigneCommandeDAO().create(c));
	}

	@Test
	public void testUpdateCasGeneral() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		LigneCommande c = (LigneCommande) daos.getLigneCommandeDAO().getById(1);
		c.setTarif("test");
		assertTrue(daos.getClientDAO().update(c));
	}

	@Test
	public void testDeleteCasGeneral() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		LigneCommande c = (LigneCommande) daos.getLigneCommandeDAO().getById(9);
		assertTrue(daos.getClientDAO().delete(c));
	}

	@Test
	public void testDeleteInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertFalse(daos.getLigneCommandeDAO().delete(daos.getLigneCommandeDAO().getById(11)));
	}

	@Test
	public void testUpdateInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertFalse(daos.getLigneCommandeDAO().update(daos.getLigneCommandeDAO().getById(11)));
	}

	@Test
	public void getById() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		int i = ((LigneCommande) daos.getLigneCommandeDAO().getById(1)).getquantite();
		assertEquals(i, 1);
	}

	@Test
	public void getByIdInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertEquals(daos.getLigneCommandeDAO().getById(12), null);
	}
}
