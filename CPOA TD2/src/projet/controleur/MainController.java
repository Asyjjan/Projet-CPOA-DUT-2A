package projet.controleur;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController {

	@FXML private Button buttonClient;
	@FXML private Button buttonProduit;
	@FXML private Button buttonCategorie;
	@FXML private Button buttonCommande;
	@FXML private Button buttonLigneCommande;
	
	@FXML public void initialize() throws IOException{
	}

	
	@FXML public void clickOnClient(ActionEvent e) throws IOException {
		Parent Client = FXMLLoader.load(getClass().getResource("/projet/FXML/pageclient.fxml"));
		Scene Clientscene = new Scene(Client);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(Clientscene);
		window.centerOnScreen();
		window.setTitle("Page client");
		window.show();
	}
	
	@FXML public void clickOnProduit(ActionEvent e) throws IOException {
		Parent Produit = FXMLLoader.load(getClass().getResource("/projet/FXML/pageproduit.fxml"));
		Scene Produitscene = new Scene(Produit);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(Produitscene);
		window.centerOnScreen();
		window.setTitle("Page produit");
		window.show();
	}
	
	@FXML public void clickOnCategorie(ActionEvent e) throws IOException {
		Parent categorie = FXMLLoader.load(getClass().getResource("/projet/FXML/pagecategorie.fxml"));
		Scene categorieScene = new Scene(categorie);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(categorieScene);
		window.centerOnScreen();
		window.setTitle("Page catégorie");
		window.show();
	}
	
	@FXML public void clickOnCommande(ActionEvent e) throws IOException {
		Parent commande = FXMLLoader.load(getClass().getResource("/projet/FXML/pagecommande.fxml"));
		Scene commandeScene = new Scene(commande);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(commandeScene);
		window.centerOnScreen();
		window.setTitle("Page commande");
		window.show();
	}

}
