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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Commande;
import projet.metier.LigneCommande;
import projet.metier.Produit;

public class EditLigneCommandeController {

	@FXML
	private Button buttonReturn;
	@FXML
	private Button buttonValider;
	@FXML
	private Label labelAffichage;
	@FXML
	private TextField textFieldLigneCommandeQuantite;

	@FXML
	public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		buttonValider.setDisable(true);
		LigneCommande lignecom = dao.getLigneCommandeDAO().getById(PageCommandeController.getLigneCommande().getIdcom(), PageCommandeController.getLigneCommande().getIdprod());
		textFieldLigneCommandeQuantite.setText(String.valueOf(lignecom.getQuantite()));
	}

	@FXML
	public void clickOnReturn(ActionEvent e) throws IOException {
		Parent LigneCommande = FXMLLoader.load(getClass().getResource("/projet/FXML/pagecommande.fxml"));
		Scene LigneCommandescene = new Scene(LigneCommande);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(LigneCommandescene);
		window.centerOnScreen();
		window.setTitle("Page ligneCommande");
		window.show();
	}

	@FXML
	public void majLabelAffichage(ActionEvent e) throws SQLException, IOException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		int quantite = Integer.valueOf(textFieldLigneCommandeQuantite.getText().trim());
		LigneCommande lignecom = dao.getLigneCommandeDAO().getById(PageCommandeController.getLigneCommande().getIdcom(), PageCommandeController.getLigneCommande().getIdprod());
		LigneCommande lignecom2 = new LigneCommande(lignecom.getIdcom(), lignecom.getIdprod(), quantite, lignecom.getTarif());
		dao.getLigneCommandeDAO().update(lignecom2);
		clickOnReturn(e);
	}

	@FXML
	public void keyReleasedProperty() {
		int quantite = Integer.valueOf(textFieldLigneCommandeQuantite.getText().trim());

		boolean isDisabled = (quantite == 0);
		buttonValider.setDisable(isDisabled);
	}
}
