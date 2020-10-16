package projet.controleur;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Categorie;
import projet.metier.Produit;

public class AjoutProduitController{

	@FXML private TextField textFieldNomProduit;
	@FXML private TextArea textAreaDescProduit;
	@FXML private TextField textFieldTarifProduit;
	@FXML private Label labelAffichage;
	@FXML private ChoiceBox<Categorie> choiceBoxCateg;
	@FXML private Button buttonValider;

	@FXML public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		ObservableList<Categorie> categorie= FXCollections.observableArrayList(dao.getCategorieDAO().findAll());
		choiceBoxCateg.setItems(categorie);
		buttonValider.setDisable(true);
	}
	
	@FXML public void majLabelAffichage() throws SQLException{
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		String nom = textFieldNomProduit.getText().trim();
		String desc = textAreaDescProduit.getText().trim();
		String str = textFieldTarifProduit.getText().trim();
		Categorie Categ = choiceBoxCateg.getValue();

		if(Categ == null) {
			labelAffichage.setText("Veuillez remplir tous les champs");
			labelAffichage.setStyle("-fx-text-fill: red; -fx-font-size: 11pt;");
		}
		else {
			float tarif = Float.parseFloat(str);
			labelAffichage.setText(nom + ", (" + Categ + "), " + tarif + " euros");
			labelAffichage.setStyle("-fx-text-fill: black; -fx-font-size: 11pt;");
			Produit prod = new Produit(15, nom, desc, tarif, "*", Categ.getIdcateg());
			dao.getProduitDAO().create(prod);
		}
	}
	
	@FXML public void keyReleasedProperty() {
		String nom = textFieldNomProduit.getText();
		String desc = textAreaDescProduit.getText();
		String tarif = textFieldTarifProduit.getText();

		boolean isDisabled = (nom.isEmpty() || nom.trim().isEmpty() || desc.isEmpty() || desc.trim().isEmpty() || tarif.isEmpty() || tarif.trim().isEmpty());
		buttonValider.setDisable(isDisabled);
	}
}
