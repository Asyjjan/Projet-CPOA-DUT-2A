package projet.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.dao.ListeMemoire.ListeMemoireCategorieDAO;
import projet.dao.MYSQLDAO.MySQLCategorieDAO;
import projet.metier.Categorie;

public class MenuCategorie {

	public static void menuCategorie(int bdd) throws SQLException {
		
		int persistance = bdd;
		System.out.println("Bonjour, voici le menu des catégories.");
		System.out.println("Pour ajouter une catégorie, taper 1");
		System.out.println("Pour modifier une catégorie, taper 2");
		System.out.println("Pour supprimer une catégorie, taper 3");
		System.out.println("Pour afficher toutes les catégories, taper 4");
		System.out.println("Pour afficher une catégorie, taper 5");

		Scanner sct1 = new Scanner(System.in);
		Scanner sct2 = new Scanner(System.in);
		Scanner sct3 = new Scanner(System.in);
		int ct = sct1.nextInt();

		switch (ct) {
		case 1:
		{
			System.out.println("Afin d'ajouter une catégorie, respecter bien l'ordre des données Ã  écrire :");
			System.out.println("ID catégorie");
			System.out.println("TItre catégorie");
			System.out.println("URL visuel catégorie");
			int idcateg = sct1.nextInt();
			String titrecateg = sct2.nextLine();
			String visuel = sct3.nextLine();
			MySQLCategorieDAO.create(idcateg, titrecateg, visuel);
		}
		break;

		case 2:
		{
			System.out.println("Afin de modifier une catégorie, respecter bien l'ordre des données Ã  écrire :");
			System.out.println("ID catégorie");
			System.out.println("TItre catégorie Ã  modifié");
			System.out.println("URL visuel catégorie Ã  modifié");
			int idcateg = sct1.nextInt();
			String titrecateg = sct2.nextLine();
			String visuel = sct3.nextLine();
			if(persistance == 1) {
			MySQLCategorieDAO.update(idcateg, titrecateg, visuel);
			}
			else if(persistance == 2) {
				DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE).getCategorieDAO().create(new Categorie(idcateg,titrecateg,visuel));
			}
		}
		break;

		case 3:
		{
			System.out.println("Afin de supprimer une catégorie, veuillez renseigner l'ID de la catégorie :");
			int idcateg = sct1.nextInt();
			MySQLCategorieDAO.delete(idcateg);
		}
		break;


		case 4:
		{
			System.out.println("Vous avez demander Ã  voir l'ensemble des catégories :");
			ArrayList<Categorie> liste= MySQLCategorieDAO.Categorie();
			for(Categorie c : liste)
			{
				System.out.println(c.toString());
			}
		}
		break;
		}

	}
}
