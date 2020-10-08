package projet.dao.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Categorie;

public class MYSQLCategorieDAOTest {

	@Test
	public void testCreateCasGeneral() throws SQLException {
		Categorie c = new Categorie(9, "test", "test");
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertTrue(daos.getCategorieDAO().create(c));
	}

	@Test
	public void testCreateExistant() throws SQLException {
		Categorie c = new Categorie(1, "test", "test");
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertFalse(daos.getCategorieDAO().create(c));
	}

	@Test
	public void testUpdateCasGeneral() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		Categorie c = (Categorie) daos.getCategorieDAO().getById(1);
		c.setTitre("test");
		assertTrue(daos.getCategorieDAO().update(c));
	}

	@Test
	public void testDeleteCasGeneral() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		Categorie c = (Categorie) daos.getCategorieDAO().getById(9);
		assertTrue(daos.getCategorieDAO().delete(c));
	}

	@Test
	public void testDeleteInexistant() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertFalse(daos.getCategorieDAO().delete(daos.getCategorieDAO().getById(11)));
	}

	@Test
	public void testUpdateInexistant() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertFalse(daos.getCategorieDAO().update(daos.getCategorieDAO().getById(11)));
	}

	@Test
	public void getById() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		int i = ((Categorie) daos.getCategorieDAO().getById(1)).getIdcateg();
		assertEquals(i, 1);
	}

	@Test
	public void getByIdInexistant() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.MYSQL);
		assertEquals(daos.getCategorieDAO().getById(12), null);
	}

}
