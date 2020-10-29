package projet.controleur;

import java.io.IOException;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Categorie;
import projet.metier.Client;
import projet.metier.Produit;

public class EditProduitController {

	@FXML private Button buttonReturn;
	@FXML private Button buttonValider;
	@FXML private Label labelAffichage;
	@FXML private TextField textFieldNomProduit;
	@FXML private TextArea textAreaDescProduit;
	@FXML private TextField textFieldTarifProduit;
	@FXML private ChoiceBox<Categorie> choiceBoxCateg;
	
	@FXML public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		int idprod = PageProduitController.getProduit().getIdprod();
		Produit produit = dao.getProduitDAO().getById(idprod);
		ObservableList<Categorie> categorie= FXCollections.observableArrayList(dao.getCategorieDAO().findAll());
		choiceBoxCateg.setItems(categorie);
		choiceBoxCateg.setValue(dao.getCategorieDAO().getById(produit.getIdcateg()));
		buttonValider.setDisable(true);
		textFieldNomProduit.setText(produit.getNom());
		textAreaDescProduit.setText(produit.getDesc());
		textFieldTarifProduit.setText(String.valueOf(produit.getTarif()));
	}
	
	
	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent Produit = FXMLLoader.load(getClass().getResource("/projet/FXML/pageproduit.fxml"));
		Scene Produitscene = new Scene(Produit);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(Produitscene);
		window.centerOnScreen();
		window.setTitle("Produit");
		window.show();
	}
	
	@FXML public void majLabelAffichage(ActionEvent e) throws SQLException, IOException{
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		int id = PageProduitController.getProduit().getIdprod();
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
			Produit prod = new Produit(id, nom, desc, tarif, "*", Categ.getIdcateg());
			dao.getProduitDAO().update(prod);
			clickOnReturn(e);
		}
		}
	
	@FXML public void keyReleasedProperty() {
			String nom = textFieldNomProduit.getText();
			String desc = textAreaDescProduit.getText();
			String tarif = textFieldTarifProduit.getText();
			Categorie Categ = choiceBoxCateg.getValue();

			boolean isDisabled = (nom.isEmpty() || nom.trim().isEmpty() || desc.isEmpty() || desc.trim().isEmpty() || tarif.isEmpty() || tarif.trim().isEmpty() || Categ == null);
			buttonValider.setDisable(isDisabled);
		}

}