package projet.controleur;

import java.sql.SQLException;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import projet.dao.DAOFactory;
import projet.dao.Persistance;
import projet.metier.Client;
import projet.metier.Commande;
import projet.metier.Produit;

public class AjoutCommandeController {

	@FXML private ChoiceBox<Produit> choiceBoxProduit;
	@FXML private DatePicker date;
	@FXML private TextField textFieldDate;
	@FXML private ChoiceBox<Client> choiceBoxClient;
	@FXML private Button buttonValider;
	@FXML private Label labelAffichage;
	
	@FXML public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		buttonValider.setDisable(true);
	}
	
	@FXML public void majLabelAffichage() throws SQLException{
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		Produit produit = choiceBoxProduit.getValue();
		LocalDate date = textFieldDate.getText();
		Client client = choiceBoxClient.getValue();

		labelAffichage.setText("La commande a été crée : " + produit + " acheté le " + date + " par " + client);
		labelAffichage.setStyle("-fx-text-fill: black; -fx-font-size: 11pt;");
		Commande commande = new Commande(0, date, client.getIdclient(), null);
		dao.getCommandeDAO().create(commande);
		}
}
