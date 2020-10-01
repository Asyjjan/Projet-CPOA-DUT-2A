package projet.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import projet.dao.MYSQLDAO.MySQLClientDAO;
import projet.metier.Client;

public class MenuClient {

	public static void menuClient() throws SQLException {

		System.out.println("Bonjour, voici le menu des clients.");
		System.out.println("Pour ajouter un client, taper 1");
		System.out.println("Pour modifier un client, taper 2");
		System.out.println("Pour supprimer un client, taper 3");
		System.out.println("Pour afficher tous les clients, taper 4");

		Scanner scc1 = new Scanner(System.in);
		Scanner scc2 = new Scanner(System.in);
		Scanner scc3 = new Scanner(System.in);
		int c = scc1.nextInt();

		switch (c) {
		case 1:
		{
			System.out.println("Afin d'ajouter un client, respecter bien l'ordre des données Ã  écrire :");
			System.out.println("ID client");
			System.out.println("Nom client");
			System.out.println("Prenom client");
			int idclient = scc1.nextInt();
			String nom = scc2.nextLine();
			String prenom = scc3.nextLine();
			MySQLClientDAO.create(idclient, nom, prenom);
		}
		break;

		case 2:
		{
			System.out.println("Afin de modifier un client, respecter bien l'ordre des données Ã  écrire :");
			System.out.println("ID client");
			System.out.println("Nom client à modifié");
			System.out.println("Prenom client à modifié");
			int idclient = scc1.nextInt();
			String nom = scc2.nextLine();
			String prenom = scc3.nextLine();
			MySQLClientDAO.update(idclient, nom, prenom);
		}
		break;

		case 3:
		{
			System.out.println("Afin de supprimer un client, veuillez renseigner l'ID du client :");
			int idclient = scc1.nextInt();
			MySQLClientDAO.delete(idclient);
		}
		break;


		case 4:
		{
			System.out.println("Vous avez demander Ã  voir l'ensemble des clients :");
			ArrayList<Client> liste2= MySQLClientDAO.Client();
			for(Client cl : liste2)
			{
				System.out.println(cl.toString());
			}
		}
		break;
		}
	}
}
