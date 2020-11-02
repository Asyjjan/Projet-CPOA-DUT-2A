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
import javafx.stage.Stage;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Client;
import projet.metier.Commande;

public class AddCommandeController {
	
	@FXML private ChoiceBox<Client> choiceBoxClient;
	@FXML private DatePicker DateCommande;
	@FXML private Button buttonValider;
	@FXML private Button buttonReturn;
	
	@FXML public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		ObservableList<Client> client= FXCollections.observableArrayList(dao.getClientDAO().findAll());
		choiceBoxClient.setItems(client);
		buttonValider.setDisable(true);
	}
	
	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent addLigneCommande = FXMLLoader.load(getClass().getResource("/projet/FXML/pagecommande.fxml"));
		Scene addLigneCommandescene = new Scene(addLigneCommande);
		Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
		window.setScene(addLigneCommandescene);
		window.centerOnScreen();
		window.setTitle("Page commande");
		window.show();
	}
	
	@FXML public void clickOnAdd(ActionEvent e) throws IOException, SQLException{
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		LocalDate date = DateCommande.getValue();
		Client cl = choiceBoxClient.getValue();
		Commande commande = new Commande(0, date, cl.getIdclient(), null);
		dao.getCommandeDAO().create(commande);
	}
	
	@FXML
	public void keyReleasedProperty() {
		String date = String.valueOf(DateCommande.getValue());
		Client cl1 = choiceBoxClient.getValue();

		boolean isDisabled = (date.isEmpty() || date.trim().isEmpty() || cl1 == null);
		buttonValider.setDisable(isDisabled);
	}
}
