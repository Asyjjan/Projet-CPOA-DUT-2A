package projet.menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException{

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
			MenuCategorie.menuCategorie();
		}

		break;

		case 2:
		{
			MenuClient.menuClient();
		}
		break;

		case 3:
		{
			MenuProduit.menuProduit();
		}
		break;
		
		case 4:
		{
			MenuCommande.menuCommande();
		}
		break;
		
		case 5:
		{
			MenuLigneCommande.menuLigneCommande();
		}
		break;

		}
	}
}