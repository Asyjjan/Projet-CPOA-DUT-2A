package projet.dao.test;

import org.junit.Test;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Produit;

public class ListeMemoireProduitDAOTest {

	@Test
	public void testCreateCasGeneral() {
		Produit c = new Produit(7, "test", "test", 40, "test", 8);
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertTrue(daos.getProduitDAO().create(c));
	}

	@Test
	public void testCreateExistant() {
		Produit c = new Produit(1, "test", "test", 40, "test", 8);
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getProduitDAO().create(c));
	}

	@Test
	public void testUpdateCasGeneral() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		Produit c = (Produit) daos.getProduitDAO().getById(1);
		c.setNom("sdf");
		assertTrue(daos.getProduitDAO().update(c));
	}

	@Test
	public void testDeleteCasGeneral() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		Produit c = (Produit) daos.getProduitDAO().getById(1);
		assertTrue(daos.getProduitDAO().delete(c));
	}

	@Test
	public void testDeleteInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getProduitDAO().delete(daos.getProduitDAO().getById(11)));
	}

	@Test
	public void testUpdateInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getProduitDAO().update(daos.getProduitDAO().getById(11)));
	}

	@Test
	public void getById() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		int i = ((Produit) daos.getProduitDAO().getById(1)).getIdprod();
		assertEquals(i, 1);
	}

	@Test
	public void getByIdInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertEquals(daos.getProduitDAO().getById(12), null);
	}
}
