package projet.dao.test;

import org.junit.Test;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Categorie;

public class ListeMemoireCategorieDAOTest {

	@Test
	public void testCreateCasGeneral() {
		Categorie c = new Categorie(7, "test", "test");
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertTrue(daos.getCategorieDAO().create(c));
	}

	@Test
	public void testCreateExistant() {
		Categorie c = new Categorie(1, "test", "test");
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getCategorieDAO().create(c));
	}

	@Test
	public void testUpdateCasGeneral() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		Categorie c = (Categorie) daos.getCategorieDAO().getById(1);
		c.setTitre("sdf");
		assertTrue(daos.getCategorieDAO().update(c));
	}

	@Test
	public void testDeleteCasGeneral() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		Categorie c = (Categorie) daos.getCategorieDAO().getById(1);
		assertTrue(daos.getCategorieDAO().delete(c));
	}

	@Test
	public void testDeleteInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getCategorieDAO().delete(daos.getCategorieDAO().getById(11)));
	}

	@Test
	public void testUpdateInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getCategorieDAO().update(daos.getCategorieDAO().getById(11)));
	}

	@Test
	public void getById() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		int i = ((Categorie) daos.getCategorieDAO().getById(1)).getIdcateg();
		assertEquals(i, 1);
	}

	@Test
	public void getByIdInexistant() {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertEquals(daos.getCategorieDAO().getById(12), null);
	}
}
