package projet.menu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.dao.MYSQLDAO.MySQLCategorieDAO;
import projet.dao.MYSQLDAO.MySQLClientDAO;
import projet.metier.Categorie;
import projet.metier.Client;

public class MenuClient {

	public static void menuClient(int bdd) throws SQLException {

		int persistance = bdd;
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
			if(persistance == 1) {
				MySQLClientDAO.create(idclient, nom, prenom);
			}
			else if(persistance == 2) {
				DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE).getClientDAO().create(new Client(idclient,nom,prenom, null, null, null, null, null, null, null));
			}
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
			if(persistance == 1) {
				MySQLClientDAO.update(idclient, nom, prenom);
			}
			else if(persistance == 2) {
				DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE).getClientDAO().update(new Client(idclient,nom,prenom, null, null, null, null, null, null, null));
			}
		}
		break;

		case 3:
		{
			System.out.println("Afin de supprimer un client, veuillez renseigner l'ID du client :");
			int idclient = scc1.nextInt();
			if(persistance == 1) {
				MySQLClientDAO.delete(idclient);
			}
			else if(persistance == 2) {
				DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE).getClientDAO().delete(new Client(idclient,null,null, null, null, null, null, null, null, null));
			}
		}
		break;


		case 4:
		{
			System.out.println("Vous avez demander Ã  voir l'ensemble des clients :");
			if(persistance == 1) {
				ArrayList<Client> liste2= MySQLClientDAO.Client();
				for(Client cl : liste2)
				{
					System.out.println(cl.toString());
				}
			}
			else if(persistance == 2) {
				DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE).getClientDAO().findAll();
			}
		}
		break;
		}
	}
}
