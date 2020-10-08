package projet.dao.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

import org.junit.Test;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Commande;
import projet.metier.LigneCommande;
import projet.metier.Produit;

public class ListeMemoireCommandeDAOTest {

	@Test
	public void testCreateCasGeneral() throws SQLException {
		HashMap<Produit, LigneCommande> ligneCommande = new HashMap<Produit, LigneCommande>();
		LocalDate date = LocalDate.now();
		Commande c = new Commande(9,date, 9, ligneCommande);
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertTrue(daos.getCommandeDAO().create(c));
	}

	@Test
	public void testCreateExistant() throws SQLException {
		HashMap<Produit, LigneCommande> ligneCommande = new HashMap<Produit, LigneCommande>();
		LocalDate date = LocalDate.now();
		Commande c = new Commande(9,date, 9, ligneCommande);
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getCommandeDAO().create(c));
	}

	@Test
	public void testUpdateCasGeneral() throws SQLException {
		LocalDate date = LocalDate.now();
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		Commande c = (Commande) daos.getCommandeDAO().getById(1);
		c.setDatecommande(date);
		assertTrue(daos.getCommandeDAO().update(c));
	}

	@Test
	public void testDeleteCasGeneral() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		Commande c = (Commande) daos.getCommandeDAO().getById(1);
		assertTrue(daos.getCommandeDAO().delete(c));
	}

	@Test
	public void testDeleteInexistant() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getCommandeDAO().delete(daos.getCommandeDAO().getById(11)));
	}

	@Test
	public void testUpdateInexistant() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertFalse(daos.getCommandeDAO().update(daos.getCommandeDAO().getById(11)));
	}

	@Test
	public void getById() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		int i = ((Commande) daos.getCommandeDAO().getById(1)).getIdcommande();
		assertEquals(i, 1);
	}

	@Test
	public void getByIdInexistant() throws SQLException {
		DAOFactory daos = DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE);
		assertEquals(daos.getCommandeDAO().getById(12), null);
	}
}
