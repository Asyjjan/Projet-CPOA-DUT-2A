package projet.menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException{

		int bdd =0;
		System.out.println("Bonjour, voici le menu de l'application.");
		System.out.println("Pour accéder au menu des catégories, taper 1");
		System.out.println("Pour accéder au menu des clients, taper 2");
		System.out.println("Pour accéder au menu des produits, taper 3");
		System.out.println("Pour accéder au menu des commandes, taper 4");
		System.out.println("Pour accéder au menu des lignes de commande, taper 5");

		Scanner scm = new Scanner(System.in);
		int m = scm.nextInt();

		switch (m) {
		case 1:
		{
			Scanner d = new Scanner(System.in);
			System.out.println("Comment voulez vous enregistrer les données ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
			bdd = d.nextInt();
			if(bdd <1 || bdd>2) {
				System.out.println("Comment voulez vous enregistrer les données ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
				bdd = d.nextInt();
			}
			MenuCategorie.menuCategorie(bdd);
		}

		break;

		case 2:
		{
			Scanner d = new Scanner(System.in);
			System.out.println("Comment voulez vous enregistrer les données ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
			bdd = d.nextInt();
			if(bdd <1 || bdd>2) {
				System.out.println("Comment voulez vous enregistrer les données ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
				bdd = d.nextInt();
			}
			MenuClient.menuClient(bdd);
		}
		break;

		case 3:
		{
			Scanner d = new Scanner(System.in);
			System.out.println("Comment voulez vous enregistrer les données ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
			bdd = d.nextInt();
			if(bdd <1 || bdd>2) {
				System.out.println("Comment voulez vous enregistrer les données ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
				bdd = d.nextInt();
			}
			MenuProduit.menuProduit(bdd);
		}
		break;

		case 4:
		{
			Scanner d = new Scanner(System.in);
			System.out.println("Comment voulez vous enregistrer les données ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
			bdd = d.nextInt();
			if(bdd <1 || bdd>2) {
				System.out.println("Comment voulez vous enregistrer les données ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
				bdd = d.nextInt();
			}
			MenuCommande.menuCommande(bdd);
		}
		break;

		case 5:
		{
			Scanner d = new Scanner(System.in);
			System.out.println("Comment voulez vous enregistrer les données ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
			bdd = d.nextInt();
			if(bdd <1 || bdd>2) {
				System.out.println("Comment voulez vous enregistrer les données ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
				bdd = d.nextInt();
			}
			MenuLigneCommande.menuLigneCommande(bdd);
		}
		break;

		}
	}
}