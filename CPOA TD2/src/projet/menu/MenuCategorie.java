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
		System.out.println("Bonjour, voici le menu des cat�gories.");
		System.out.println("Pour ajouter une cat�gorie, taper 1");
		System.out.println("Pour modifier une cat�gorie, taper 2");
		System.out.println("Pour supprimer une cat�gorie, taper 3");
		System.out.println("Pour afficher toutes les cat�gories, taper 4");
		System.out.println("Pour afficher une cat�gorie, taper 5");

		Scanner sct1 = new Scanner(System.in);
		Scanner sct2 = new Scanner(System.in);
		Scanner sct3 = new Scanner(System.in);
		int ct = sct1.nextInt();

		switch (ct) {
		case 1:
		{
			System.out.println("Afin d'ajouter une cat�gorie, respecter bien l'ordre des donn�es à �crire :");
			System.out.println("ID cat�gorie");
			System.out.println("TItre cat�gorie");
			System.out.println("URL visuel cat�gorie");
			int idcateg = sct1.nextInt();
			String titrecateg = sct2.nextLine();
			String visuel = sct3.nextLine();
			MySQLCategorieDAO.create(idcateg, titrecateg, visuel);
		}
		break;

		case 2:
		{
			System.out.println("Afin de modifier une cat�gorie, respecter bien l'ordre des donn�es à �crire :");
			System.out.println("ID cat�gorie");
			System.out.println("TItre cat�gorie à modifi�");
			System.out.println("URL visuel cat�gorie à modifi�");
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
			System.out.println("Afin de supprimer une cat�gorie, veuillez renseigner l'ID de la cat�gorie :");
			int idcateg = sct1.nextInt();
			MySQLCategorieDAO.delete(idcateg);
		}
		break;


		case 4:
		{
			System.out.println("Vous avez demander à voir l'ensemble des cat�gories :");
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
