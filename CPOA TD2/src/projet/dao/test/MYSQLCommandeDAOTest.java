package projet.dao.test;

import org.junit.Test;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Commande;

public class MYSQLCommandeDAOTest {

	public void testCreateCasGeneral() {
		Commande c = new Commande(9, "test", 8, "test");
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertTrue(daos.getCommandeDAO().create(c));
	}

	@Test
	public void testCreateExistant() {
		Commande c = new Commande(1, "test", "test", "test");
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertFalse(daos.getCommandeDAO().create(c));
	}

	@Test
	public void testUpdateCasGeneral() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		Commande c = (Commande) daos.getCommandeDAO().getById(1);
		c.setDatecommande("test");
		assertTrue(daos.getCommandeDAO().update(c));
	}

	@Test
	public void testDeleteCasGeneral() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		Commande c = (Commande) daos.getCommandeDAO().getById(9);
		assertTrue(daos.getCommandeDAO().delete(c));
	}

	@Test
	public void testDeleteInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertFalse(daos.getCommandeDAO().delete(daos.getCommandeDAO().getById(11)));
	}

	@Test
	public void testUpdateInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertFalse(daos.getCommandeDAO().update(daos.getCommandeDAO().getById(11)));
	}

	@Test
	public void getById() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		int i = ((Commande) daos.getCommandeDAO().getById(1)).getIdcommande();
		assertEquals(i, 1);
	}

	@Test
	public void getByIdInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertEquals(daos.getCommandeDAO().getById(12), null);
	}
}
