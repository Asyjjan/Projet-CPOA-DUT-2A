package projet.dao.test;

import org.junit.Test;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.LigneCommande;

public class ListeMemoireLigneCommandeDAOTest {

	@Test
	public void testCreateCasGeneral() {
		LigneCommande c = new LigneCommande(7, 20);
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertTrue(daos.getLigneCommandeDAO().create(c));
	}

	@Test
	public void testCreateExistant() {
		LigneCommande c = new LigneCommande(1, 15);
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getLigneCommandeDAO().create(c));
	}

	@Test
	public void testUpdateCasGeneral() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		LigneCommande c = (LigneCommande) daos.getLigneCommandeDAO().getById(1);
		c.setTarif(20);
		assertTrue(daos.getLigneCommandeDAO().update(c));
	}

	@Test
	public void testDeleteCasGeneral() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		LigneCommande c = (LigneCommande) daos.getLigneCommandeDAO().getById(1);
		assertTrue(daos.getLigneCommandeDAO().delete(c));
	}

	@Test
	public void testDeleteInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getLigneCommandeDAO().delete(daos.getLigneCommandeDAO().getById(11)));
	}

	@Test
	public void testUpdateInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getLigneCommandeDAO().update(daos.getLigneCommandeDAO().getById(11)));
	}

	@Test
	public void getById() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		int i = ((LigneCommande) daos.getLigneCommandeDAO().getById(1)).getquantite();
		assertEquals(i, 1);
	}

	@Test
	public void getByIdInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertEquals(daos.getLigneCommandeDAO().getById(12), null);
	}
}
