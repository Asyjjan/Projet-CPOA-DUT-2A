package projet.dao.test;

import org.junit.Test;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Commande;

public class ListeMemoireCommandeDAOTest {

	@Test
	public void testCreateCasGeneral() {
		Commande c = new Commande(7, "test", "test", "test");
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertTrue(daos.getCommandeDAO().create(c));
	}

	@Test
	public void testCreateExistant() {
		Commande c = new Commande(1, "test", "test", "test");
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getCommandeDAO().create(c));
	}

	@Test
	public void testUpdateCasGeneral() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		Commande c = (Commande) daos.getCommandeDAO().getById(1);
		c.setDatecommande("sdf");
		assertTrue(daos.getCommandeDAO().update(c));
	}

	@Test
	public void testDeleteCasGeneral() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		Commande c = (Commande) daos.getCommandeDAO().getById(1);
		assertTrue(daos.getCommandeDAO().delete(c));
	}

	@Test
	public void testDeleteInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getCommandeDAO().delete(daos.getCommandeDAO().getById(11)));
	}

	@Test
	public void testUpdateInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getCommandeDAO().update(daos.getCommandeDAO().getById(11)));
	}

	@Test
	public void getById() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		int i = ((Commande) daos.getCommandeDAO().getById(1)).getIdcommande();
		assertEquals(i, 1);
	}

	@Test
	public void getByIdInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertEquals(daos.getCommandeDAO().getById(12), null);
	}
}
