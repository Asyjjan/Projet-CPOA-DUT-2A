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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Categorie;
import projet.metier.Client;

public class EditCategorieController {

	@FXML private Button buttonReturn;
	@FXML private Button buttonValider;
	@FXML private Label labelAffichage;
	@FXML private TextField textFieldTitreCategorie;
	@FXML private TextField textFieldVisuelCategorie;
	
	@FXML public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		buttonValider.setDisable(true);
		PageCategorieController.getCategorie();
	}
	
	
	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent Categorie = FXMLLoader.load(getClass().getResource("/projet/FXML/pagecategorie.fxml"));
		Scene Categoriescene = new Scene(Categorie);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(Categoriescene);
		window.centerOnScreen();
		window.setTitle("Categorie");
		window.show();
	}
	
	@FXML public void majLabelAffichage() throws SQLException{
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		int id = PageCategorieController.getCategorie().getIdcateg();
		String titre = textFieldTitreCategorie.getText().trim();
		String visuel = textFieldVisuelCategorie.getText().trim();

		labelAffichage.setText(titre + "," + visuel);
		labelAffichage.setStyle("-fx-text-fill: black; -fx-font-size: 11pt;");
		
		Categorie categorie = new Categorie(id, titre, visuel);
		dao.getCategorieDAO().update(categorie);
		}
	
	@FXML public void keyReleasedProperty() {
		String titre = textFieldTitreCategorie.getText();
		String visuel = textFieldVisuelCategorie.getText();

		boolean isDisabled = (titre.isEmpty() || titre.trim().isEmpty() || visuel.isEmpty() || visuel.trim().isEmpty());
		buttonValider.setDisable(isDisabled);
	}

}