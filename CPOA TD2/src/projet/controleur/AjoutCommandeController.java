package projet.controleur;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Categorie;
import projet.metier.Client;
import projet.metier.Commande;
import projet.metier.Produit;

public class AjoutCommandeController {

	@FXML private DatePicker datePickerCommande;
	@FXML private ChoiceBox<Client> choiceBoxClient;
	@FXML private Button buttonValider;
	@FXML private Label labelAffichage;
	
	@FXML public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		ObservableList<Client> client= FXCollections.observableArrayList(dao.getClientDAO().findAll());
		choiceBoxClient.setItems(client);
		buttonValider.setDisable(true);
	}
	
	@FXML public void majLabelAffichage() throws SQLException{
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		LocalDate date = datePickerCommande.getValue();
		Client client = choiceBoxClient.getValue();

		labelAffichage.setText("La commande a �t� cr�e : " + " achet� le " + date + " par " + client);
		labelAffichage.setStyle("-fx-text-fill: black; -fx-font-size: 11pt;");
		Commande commande = new Commande(0, date, client.getIdclient(), null);
		dao.getCommandeDAO().create(commande);
		}
	
	@FXML
	public void clickOnReturn(ActionEvent e) throws IOException {
		Parent returnLigneCommande = FXMLLoader.load(getClass().getResource("/projet/FXML/main.fxml"));
		Scene returnLigneCommandescene = new Scene(returnLigneCommande);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(returnLigneCommandescene);
		window.centerOnScreen();
		window.setTitle("LigneCommande");
		window.show();
	}
	
	@FXML public void keyReleasedProperty() {
		LocalDate date = datePickerCommande.getValue();
		Client cl = choiceBoxClient.getSelectionModel().getSelectedItem();

		boolean isDisabled = (date == null || cl == null);
		buttonValider.setDisable(isDisabled);
	}
}
