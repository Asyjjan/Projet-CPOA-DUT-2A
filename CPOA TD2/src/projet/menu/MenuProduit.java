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

		int persistance = bdd;
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
			System.out.println("Afin d'ajouter un produit, respecter bien l'ordre des donn�es à �crire :");
			System.out.println("ID produit");
			System.out.println("Nom produit");
			System.out.println("Description");
			System.out.println("tarif");
			System.out.println("visuel");
			System.out.println("ID cat�gorie");
			int idproduit = scp1.nextInt();
			String nom = scp2.nextLine();
			String description = scp3.nextLine();
			float tarif = scp4.nextFloat();
			String visuel = scp5.nextLine();
			int idcateg = scp6.nextInt();
			if(persistance == 1) {
				MySQLProduitDAO.create(idproduit, nom, description, tarif, visuel, idcateg);
			}
			else if(persistance == 2) {
				DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE).getProduitDAO().create(new Produit(idproduit, nom, description, tarif, visuel, idcateg));
			}
		}
		break;

		case 2:
		{
			System.out.println("Afin de modifier un produit, respecter bien l'ordre des donn�es à �crire :");
			System.out.println("ID produit");
			System.out.println("Nom produit � modifi�");
			System.out.println("Description � modifi�");
			System.out.println("tarif � modifi�");
			System.out.println("visuel � modifi�");
			System.out.println("ID cat�gorie � modifi�");
			int idproduit = scp1.nextInt();
			String nom = scp2.nextLine();
			String description = scp3.nextLine();
			float tarif = scp4.nextFloat();
			String visuel = scp5.nextLine();
			int idcateg = scp6.nextInt();
			if(persistance == 1) {
				MySQLProduitDAO.update(idproduit, nom, description, tarif, visuel, idcateg);
			}
			else if(persistance == 2) {
				DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE).getProduitDAO().update(new Produit(idproduit, nom, description, tarif, visuel, idcateg));
			}
		}
		break;

		case 3:
		{
			System.out.println("Afin de supprimer un produit, veuillez renseigner l'ID du produit :");
			int idproduit = scp1.nextInt();
			if(persistance == 1) {
				MySQLProduitDAO.delete(idproduit);
			}
			else if(persistance == 2) {
				DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE).getProduitDAO().delete(new Produit(idproduit, null, null, idproduit, null, idproduit));
			}
		}
		break;


		case 4:
		{
			System.out.println("Vous avez demander à voir l'ensemble des produits :");
			if(persistance == 1) {
				ArrayList<Produit> liste3= MySQLProduitDAO.Produit();
				for(Produit p : liste3)
				{
					System.out.println(p.toString());
				}
			}
			else if(persistance == 2) {
				DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE).getProduitDAO().findAll();
			}
		}
		break;
		}
	}

}
