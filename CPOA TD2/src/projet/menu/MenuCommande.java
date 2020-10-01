package projet.menu;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import projet.dao.MYSQLDAO.MySQLClientDAO;
import projet.dao.MYSQLDAO.MySQLCommandeDAO;
import projet.metier.Client;
import projet.metier.Commande;

public class MenuCommande {

	public static void menuCommande() {
		
		System.out.println("Bonjour, voici le menu des commandes.");
		System.out.println("Pour ajouter une commande, taper 1");
		System.out.println("Pour modifier une commande, taper 2");
		System.out.println("Pour supprimer une commande, taper 3");
		System.out.println("Pour afficher toutes les commandes, taper 4");

		Scanner scc1 = new Scanner(System.in);
		Scanner scc2 = new Scanner(System.in);
		Scanner scc3 = new Scanner(System.in);
		int c = scc1.nextInt();
		
		switch (c) {
		case 1:
		{
			System.out.println("Afin d'ajouter une commande, respecter bien l'ordre des données Ã  écrire :");
			System.out.println("ID commande");
			System.out.println("Date commande");
			System.out.println("ID client");
			int idcommande = scc1.nextInt();
			Date datecommande = scc2.nextLine();
			String idclient = scc3.nextLine();
			MySQLCommandeDAO.create(idcommande, datecommande, idclient);
		}
		break;

		case 2:
		{
			System.out.println("Afin de modifier une commande, respecter bien l'ordre des données Ã  écrire :");
			System.out.println("ID commande");
			System.out.println("Date commande à modifié");
			System.out.println("Id client à modifié");
			int idcommande = scc1.nextInt();
			Date datecommande = scc2.nextLine();
			String idclient = scc3.nextLine();
			MySQLClientDAO.update(idcommande, datecommande, idclient);
		}
		break;

		case 3:
		{
			System.out.println("Afin de supprimer une commande, veuillez renseigner l'ID du client :");
			int idcommande = scc1.nextInt();
			MySQLClientDAO.delete(idcommande);
		}
		break;


		case 4:
		{
			System.out.println("Vous avez demander Ã  voir l'ensemble des commandes :");
			ArrayList<Commande> liste2= MySQLCommandeDAO.Commande();
			for(Commande cl : liste2)
			{
				System.out.println(cl.toString());
			}
		}
		break;
		}
	}
}
