package projet.menu;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import projet.dao.MYSQLDAO.MySQLClientDAO;
import projet.dao.MYSQLDAO.MySQLCommandeDAO;
import projet.metier.Client;
import projet.metier.Commande;

public class MenuCommande {

	public static void menuCommande(int bdd) throws SQLException {
		
		int persistance = bdd;		
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
			System.out.println("Date commande \"dd/MM/yyyy\"");
			System.out.println("ID client");
			int idcommande = scc1.nextInt();
			String date = scc2.nextLine();
			DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    LocalDate datecommande = LocalDate.parse(date, formatage);
			int idclient = scc3.nextInt();
			MySQLCommandeDAO.create(idcommande, datecommande, idclient);
		}
		break;

		case 2:
		{
			System.out.println("Afin de modifier une commande, respecter bien l'ordre des données Ã  écrire :");
			System.out.println("ID commande");
			System.out.println("Date commande à modifié \"dd/MM/yyyy\" ");
			System.out.println("Id client à modifié");
			int idcommande = scc1.nextInt();
			String date = scc2.nextLine();
			DateTimeFormatter formatage = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    LocalDate datecommande = LocalDate.parse(date, formatage);
			int idclient = scc3.nextInt();
			MySQLCommandeDAO.update(idcommande, datecommande, idclient);
		}
		break;

		case 3:
		{
			System.out.println("Afin de supprimer une commande, veuillez renseigner l'ID du client :");
			int idcommande = scc1.nextInt();
			MySQLCommandeDAO.delete(idcommande);
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
