package projet.controleur;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import projet.dao.DAOFactory;
import projet.dao.Persistance;

public class CommandeController {
	
	@FXML private Button buttonAddCommande;
	@FXML private Button buttonDeleteCommande;
	
	@FXML public void initialize() throws SQLException {
	}
	
	@FXML public void clickOnAdd(ActionEvent e) throws IOException {
		Parent addLigneCommande = FXMLLoader.load(getClass().getResource("/projet/FXML/addcommande.fxml"));
		Scene addLigneCommandescene = new Scene(addLigneCommande);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(addLigneCommandescene);
		window.centerOnScreen();
		window.setTitle("Ajout d'une commande");
		window.show();
	}
	
	@FXML public void clickOnDelete(ActionEvent e) throws IOException {
		Parent addCommande = FXMLLoader.load(getClass().getResource("/projet/FXML/deletecommande.fxml"));
		Scene addCommandescene = new Scene(addCommande);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(addCommandescene);
		window.centerOnScreen();
		window.setTitle("Suppression d'une commande");
		window.show();
	}

}
