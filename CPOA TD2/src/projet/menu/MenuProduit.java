package projet.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import projet.dao.MYSQLDAO.MySQLClientDAO;
import projet.dao.MYSQLDAO.MySQLProduitDAO;
import projet.metier.Produit;

public class MenuProduit {

	public static void menuProduit() throws SQLException {
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
			MySQLProduitDAO.create(idproduit, nom, description, tarif, visuel, idcateg);
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
			MySQLProduitDAO.update(idproduit, nom, description, tarif, visuel, idcateg);
		}
		break;

		case 3:
		{
			System.out.println("Afin de supprimer un produit, veuillez renseigner l'ID du produit :");
			int idproduit = scp1.nextInt();
			MySQLProduitDAO.delete(idproduit);
		}
		break;


		case 4:
		{
			System.out.println("Vous avez demander Ã  voir l'ensemble des produits :");
			ArrayList<Produit> liste3= MySQLProduitDAO.Produit();
			for(Produit p : liste3)
			{
				System.out.println(p.toString());
			}
		}
		break;
		}
	}

}
