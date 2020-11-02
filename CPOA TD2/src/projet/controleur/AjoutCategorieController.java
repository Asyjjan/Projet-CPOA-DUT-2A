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

public class AjoutCategorieController {


	@FXML private TextField textFieldCategorieTitre;
	@FXML private TextField textFieldCategorieVisuel;
	@FXML private Label labelAffichage;
	@FXML private Button buttonValider;
	@FXML private Button buttonReturn;

	@FXML public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		buttonValider.setDisable(true);
	}
	
	@FXML public void majLabelAffichage(ActionEvent e) throws SQLException, IOException{
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		String titre = textFieldCategorieTitre.getText().trim();
		String visuel = textFieldCategorieVisuel.getText().trim();

			Categorie categ = new Categorie(0, titre, visuel);
			dao.getCategorieDAO().create(categ);
			clickOnReturn(e);
		}
	
	@FXML public void keyReleasedProperty() {
		String titre = textFieldCategorieTitre.getText();
		String visuel = textFieldCategorieVisuel.getText();

		boolean isDisabled = (titre.isEmpty() || titre.trim().isEmpty() || visuel.isEmpty() || visuel.trim().isEmpty());
		buttonValider.setDisable(isDisabled);
	}
	
	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent Client = FXMLLoader.load(getClass().getResource("/projet/FXML/pagecategorie.fxml"));
		Scene Clientscene = new Scene(Client);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(Clientscene);
		window.centerOnScreen();
		window.setTitle("Client");
		window.show();
	}
}
