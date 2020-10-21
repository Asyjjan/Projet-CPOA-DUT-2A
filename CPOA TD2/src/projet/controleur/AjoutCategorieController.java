package projet.controleur;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Categorie;

public class AjoutCategorieController {


	@FXML private TextField textFieldCategorieTitre;
	@FXML private TextField textFieldCategorieVisuel;
	@FXML private Label labelAffichage;
	@FXML private Button buttonValider;

	@FXML public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		buttonValider.setDisable(true);
	}
	
	@FXML public void majLabelAffichage() throws SQLException{
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		String titre = textFieldCategorieTitre.getText().trim();
		String visuel = textFieldCategorieVisuel.getText().trim();

			labelAffichage.setStyle("-fx-text-fill: black; -fx-font-size: 11pt;");
			labelAffichage.setText("La catégorie : " + titre + " à été ajoutée");
			Categorie categ = new Categorie(0, titre, visuel);
			dao.getCategorieDAO().create(categ);
		}
	
	@FXML public void keyReleasedProperty() {
		String titre = textFieldCategorieTitre.getText();
		String visuel = textFieldCategorieVisuel.getText();

		boolean isDisabled = (titre.isEmpty() || titre.trim().isEmpty() || visuel.isEmpty() || visuel.trim().isEmpty());
		buttonValider.setDisable(isDisabled);
	}
}
