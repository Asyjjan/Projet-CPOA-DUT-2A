package projet.dao.test;

import org.junit.Test;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Client;

public class ListeMemoireClientDAOTest {

	@Test
	public void testCreateCasGeneral() {
		Client c = new Client(7, "test", "test", "test", "test", "test", "test", "test", "test", "test");
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertTrue(daos.getClientDAO().create(c));
	}

	@Test
	public void testCreateExistant() {
		Client c = new Client(1, "test", "test", "test", "test", "test", "test", "test", "test", "test");
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getClientDAO().create(c));
	}

	@Test
	public void testUpdateCasGeneral() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		Client c = (Client) daos.getClientDAO().getById(1);
		c.setNom("sdf");
		assertTrue(daos.getClientDAO().update(c));
	}

	@Test
	public void testDeleteCasGeneral() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		Client c = (Client) daos.getClientDAO().getById(1);
		assertTrue(daos.getClientDAO().delete(c));
	}

	@Test
	public void testDeleteInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getClientDAO().delete(daos.getClientDAO().getById(11)));
	}

	@Test
	public void testUpdateInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getClientDAO().update(daos.getClientDAO().getById(11)));
	}

	@Test
	public void getById() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		int i = ((Client) daos.getClientDAO().getById(1)).getIdclient();
		assertEquals(i, 1);
	}

	@Test
	public void getByIdInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertEquals(daos.getClientDAO().getById(12), null);
	}
}
