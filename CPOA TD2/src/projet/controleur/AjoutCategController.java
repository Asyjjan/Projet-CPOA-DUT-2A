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

public class AjoutCategController{

	@FXML private TextField TextFieldNomCateg;
	@FXML private TextArea TextAreaDescCateg;
	@FXML private TextField TextFieldTarifCateg;
	@FXML private Label LabelAffichage;
	@FXML private ChoiceBox<Categorie> ChoiceBoxCateg;
	@FXML private Button ButtonValider;

	@FXML public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		ObservableList<Categorie> categorie= FXCollections.observableArrayList(dao.getCategorieDAO().findAll());
		ChoiceBoxCateg.setItems(categorie);
	}

  @FXML public void majLabelAffichage(){
//		String nom = TextFieldNomCateg.getText().trim();
//		String str = TextFieldTarifCateg.getText().trim();
//		Categorie Categ = ChoiceBoxCateg.getValue();
//
//		if(str.isEmpty() || str == null || nom == null || nom.isEmpty() || Categ == null) {
//			ButtonValider.setDisable(true);
//			LabelAffichage.setText("Veuillez remplir tous les champs");
//		}
//		else {
//			float tarif = Float.parseFloat(str);
//			LabelAffichage.setText(nom + ", (" + Categ + "), " + tarif + " euros");
//		}
	}
	
	@FXML public void keyReleasedProperty() {
		String nom = TextFieldNomCateg.getText();
		String desc = TextAreaDescCateg.getText();
		String tarif = TextFieldTarifCateg.getText();
		String categ = ChoiceBoxCateg.getValue().toString();
		
		boolean isDisabled = (nom.isEmpty() || nom.trim().isEmpty() || desc.isEmpty() || desc.trim().isEmpty() || tarif.isEmpty() || tarif.trim().isEmpty() || categ.isEmpty());
		ButtonValider.setDisable(isDisabled);
	}
}
