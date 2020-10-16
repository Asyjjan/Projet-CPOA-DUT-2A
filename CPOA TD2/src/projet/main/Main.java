package projet.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.print.DocFlavor.URL;

import javafx.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projet.menu.MenuCategorie;
import projet.menu.MenuClient;
import projet.menu.MenuCommande;
import projet.menu.MenuLigneCommande;
import projet.menu.MenuProduit;

public class Main extends Application{

	@SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException{
		
		launch(args);

		int bdd =0;
		System.out.println("Bonjour, voici le menu de l'application.");
		System.out.println("Pour acc�der au menu des cat�gories, taper 1");
		System.out.println("Pour acc�der au menu des clients, taper 2");
		System.out.println("Pour acc�der au menu des produits, taper 3");
		System.out.println("Pour acc�der au menu des commandes, taper 4");
		System.out.println("Pour acc�der au menu des lignes de commande, taper 5");

		Scanner scm = new Scanner(System.in);
		int m = scm.nextInt();

		switch (m) {
		case 1:
		{
			Scanner d = new Scanner(System.in);
			System.out.println("Comment voulez vous enregistrer les donn�es ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
			bdd = d.nextInt();
			if(bdd <1 || bdd>2) {
				System.out.println("Comment voulez vous enregistrer les donn�es ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
				bdd = d.nextInt();
			}
			MenuCategorie.menuCategorie(bdd);
		}

		break;

		case 2:
		{
			Scanner d = new Scanner(System.in);
			System.out.println("Comment voulez vous enregistrer les donn�es ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
			bdd = d.nextInt();
			if(bdd <1 || bdd>2) {
				System.out.println("Comment voulez vous enregistrer les donn�es ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
				bdd = d.nextInt();
			}
			MenuClient.menuClient(bdd);
		}
		break;

		case 3:
		{
			Scanner d = new Scanner(System.in);
			System.out.println("Comment voulez vous enregistrer les donn�es ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
			bdd = d.nextInt();
			if(bdd <1 || bdd>2) {
				System.out.println("Comment voulez vous enregistrer les donn�es ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
				bdd = d.nextInt();
			}
			MenuProduit.menuProduit(bdd);
		}
		break;

		case 4:
		{
			Scanner d = new Scanner(System.in);
			System.out.println("Comment voulez vous enregistrer les donn�es ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
			bdd = d.nextInt();
			if(bdd <1 || bdd>2) {
				System.out.println("Comment voulez vous enregistrer les donn�es ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
				bdd = d.nextInt();
			}
			MenuCommande.menuCommande(bdd);
		}
		break;

		case 5:
		{
			Scanner d = new Scanner(System.in);
			System.out.println("Comment voulez vous enregistrer les donn�es ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
			bdd = d.nextInt();
			if(bdd <1 || bdd>2) {
				System.out.println("Comment voulez vous enregistrer les donn�es ? 1 : MYSQL ; 2 : LISTE MEMOIRE");
				bdd = d.nextInt();
			}
			MenuLigneCommande.menuLigneCommande(bdd);
		}
		break;

		}
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root= FXMLLoader.load(getClass().getResource("/projet/FXML/ajoutclient.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Fenetre client");
			primaryStage.show();
			} catch (Exception e) {
			e.printStackTrace();
			}
		
	}
}