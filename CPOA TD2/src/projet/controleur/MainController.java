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
		window.setTitle("Client");
		window.show();
	}
	
	@FXML public void clickOnProduit(ActionEvent e) throws IOException {
		Parent produit = FXMLLoader.load(getClass().getResource("/projet/FXML/ajoutproduit.fxml"));
		Scene produitScene = new Scene(produit);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(produitScene);
		window.centerOnScreen();
		window.setTitle("Produit");
		window.show();
	}
	
	@FXML public void clickOnCategorie(ActionEvent e) throws IOException {
		Parent categorie = FXMLLoader.load(getClass().getResource("/projet/FXML/pagecategorie.fxml"));
		Scene categorieScene = new Scene(categorie);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(categorieScene);
		window.centerOnScreen();
		window.setTitle("Categorie");
		window.show();
	}
	
	@FXML public void clickOnCommande(ActionEvent e) throws IOException {
		Parent commande = FXMLLoader.load(getClass().getResource("/projet/FXML/ajoutcommande.fxml"));
		Scene commandeScene = new Scene(commande);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(commandeScene);
		window.centerOnScreen();
		window.setTitle("Commande");
		window.show();
	}
	
	@FXML public void clickOnLigneCommande(ActionEvent e) throws IOException {
		Parent lignecommande = FXMLLoader.load(getClass().getResource("/projet/FXML/ajoutlignecommande.fxml"));
		Scene lignecommandeScene = new Scene(lignecommande);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(lignecommandeScene);
		window.centerOnScreen();
		window.setTitle("Ligne Commande");
		window.show();
	}

}
