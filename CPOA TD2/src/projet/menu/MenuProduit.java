package projet.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.dao.MYSQLDAO.MySQLCategorieDAO;
import projet.dao.MYSQLDAO.MySQLClientDAO;
import projet.dao.MYSQLDAO.MySQLProduitDAO;
import projet.metier.Categorie;
import projet.metier.Produit;

public class MenuProduit {

	public static void menuProduit(int bdd) throws SQLException {
		
		Persistance persistance = null;
		if(bdd ==1) {
			persistance = Persistance.MYSQL;
		}
		else if(bdd ==2) {
			persistance = Persistance.LISTE_MEMOIRE;
		}
		System.out.println("Bonjour, voici le menu des produits.");
		System.out.println("Pour ajouter un produit, taper 1");
		System.out.println("Pour modifier un produit, taper 2");
		System.out.println("Pour supprimer un produit, taper 3");
		System.out.println("Pour afficher tous les produits, taper 4");

		Scanner scp1 = new Scanner(System.in);
		Scanner scp2 = new Scanner(System.in);
		Scanner scp3 = new Scanner(System.in);
		Scanner scp4 = new Scanner(System.in);
		Scanner scp5 = new Scanner(System.in);
		Scanner scp6 = new Scanner(System.in);
		int pr = scp1.nextInt();

		switch (pr) {
		case 1:
		{
			System.out.println("Afin d'ajouter un produit, respecter bien l'ordre des données Ã  écrire :");
			System.out.println("ID produit");
			System.out.println("Nom produit");
			System.out.println("Description");
			System.out.println("tarif");
			System.out.println("visuel");
			System.out.println("ID catégorie");
			int idproduit = scp1.nextInt();
			String nom = scp2.nextLine();
			String description = scp3.nextLine();
			float tarif = scp4.nextFloat();
			String visuel = scp5.nextLine();
			int idcateg = scp6.nextInt();
			Produit produit = new Produit(idproduit, nom, description, tarif, visuel, idcateg);
			DAOFactory.getDAOFactory(persistance).getProduitDAO().create(produit);
		}
		break;

		case 2:
		{
			System.out.println("Afin de modifier un produit, respecter bien l'ordre des données Ã  écrire :");
			System.out.println("ID produit");
			System.out.println("Nom produit à modifié");
			System.out.println("Description à modifié");
			System.out.println("tarif à modifié");
			System.out.println("visuel à modifié");
			System.out.println("ID catégorie à modifié");
			int idproduit = scp1.nextInt();
			String nom = scp2.nextLine();
			String description = scp3.nextLine();
			float tarif = scp4.nextFloat();
			String visuel = scp5.nextLine();
			int idcateg = scp6.nextInt();
			Produit produit = new Produit(idproduit, nom, description, tarif, visuel, idcateg);
			DAOFactory.getDAOFactory(persistance).getProduitDAO().update(produit);
		}
		break;

		case 3:
		{
			System.out.println("Afin de supprimer un produit, veuillez renseigner l'ID du produit :");
			int idproduit = scp1.nextInt();
			Produit produit = new Produit(idproduit, null, null, idproduit, null, idproduit);
			DAOFactory.getDAOFactory(persistance).getProduitDAO().delete(produit);
		}
		break;


		case 4:
		{
			System.out.println("Vous avez demander Ã  voir l'ensemble des produits :");
			ArrayList<Produit> liste = DAOFactory.getDAOFactory(persistance).getProduitDAO().findAll();
			for(Produit p : liste)
			{
				System.out.println(p.toString());
			}
		}
		break;
		}
	}

}
