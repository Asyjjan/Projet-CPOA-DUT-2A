package projet.menu;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.dao.MYSQLDAO.MySQLCategorieDAO;
import projet.dao.MYSQLDAO.MySQLClientDAO;
import projet.dao.MYSQLDAO.MySQLCommandeDAO;
import projet.dao.MYSQLDAO.MySQLLigneCommandeDAO;
import projet.metier.Categorie;
import projet.metier.Client;
import projet.metier.Commande;
import projet.metier.LigneCommande;

public class MenuLigneCommande {

	public static void menuLigneCommande(int bdd) throws SQLException {
		
		int persistance = bdd;
		System.out.println("Bonjour, voici le menu des lignes de commandes.");
		System.out.println("Pour ajouter une ligne de commande, taper 1");
		System.out.println("Pour modifier une ligne de commande, taper 2");
		System.out.println("Pour supprimer une ligne de commande, taper 3");
		System.out.println("Pour afficher toutes les lignes de commandes, taper 4");

		Scanner slc1 = new Scanner(System.in);
		Scanner slc2 = new Scanner(System.in);
		Scanner slc3 = new Scanner(System.in);
		Scanner slc4 = new Scanner(System.in);
		int c = slc1.nextInt();
		
		switch (c) {
		case 1:
		{
			System.out.println("Afin d'ajouter une ligne de commande, respecter bien l'ordre des données Ã  écrire :");
			System.out.println("ID commande");
			System.out.println("Id produit");
			System.out.println("Quantite");
			System.out.println("Tarif");
			int idcommande = slc1.nextInt();
			int idproduit = slc2.nextInt();
			int quantite = slc3.nextInt();
			float tarifunitaire = slc4.nextFloat();
			if(persistance == 1) {
				MySQLLigneCommandeDAO.create(idcommande, idproduit, quantite, tarifunitaire);
			}
			else if(persistance == 2) {
				DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE).getLigneCommandeDAO().create(new LigneCommande(idcommande, idproduit, quantite, tarifunitaire));
			}
		}
		break;

		case 2:
		{
			System.out.println("Afin de modifier une ligne de commande, respecter bien l'ordre des données Ã  écrire :");
			System.out.println("ID commande");
			System.out.println("Id produit à modifié");
			System.out.println("Quantite à modifié");
			System.out.println("Tarif à modifié");
			int idcommande = slc1.nextInt();
			int idproduit = slc2.nextInt();
			int quantite = slc3.nextInt();
			float tarifunitaire = slc4.nextFloat();
			if(persistance == 1) {
				MySQLLigneCommandeDAO.update(idcommande, idproduit, quantite, tarifunitaire);
			}
			else if(persistance == 2) {
				DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE).getLigneCommandeDAO().update(new LigneCommande(idcommande, idproduit, quantite, tarifunitaire));
			}
		}
		break;

		case 3:
		{
			System.out.println("Afin de supprimer une commande, veuillez renseigner les données :");
			System.out.println("ID commande");
			System.out.println("Id produit");
			int idcommande = slc1.nextInt();
			int idproduit = slc2.nextInt();
			if(persistance == 1) {
				MySQLLigneCommandeDAO.delete(idcommande, idproduit);
			}
			else if(persistance == 2) {
				DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE).getLigneCommandeDAO().delete(new LigneCommande(idcommande, idproduit, idproduit, idcommande));
			}
		}
		break;


		case 4:
		{
			System.out.println("Vous avez demander Ã  voir l'ensemble des lignes de commandes :");
			if(persistance == 1) {
				ArrayList<LigneCommande> liste2= MySQLLigneCommandeDAO.LigneCommande();
				for(LigneCommande lc : liste2)
				{
					System.out.println(lc.toString());
				}
			}
			else if(persistance == 2) {
				DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE).getLigneCommandeDAO().findAll();
			}
		}
		break;
		}
	}
}