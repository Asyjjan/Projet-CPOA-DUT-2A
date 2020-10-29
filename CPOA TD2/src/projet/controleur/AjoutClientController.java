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
import projet.metier.Produit;

public class AjoutClientController {
	
	@FXML private TextField textFieldNomClient;
	@FXML private TextField textFieldPrenomClient;
	@FXML private TextField textFieldIdentifiantClient;
	@FXML private TextField textFieldMdpClient;
	@FXML private TextField textFieldAdrNumClient;
	@FXML private TextField textFieldAdrVoieClient;
	@FXML private TextField textFieldCodePostalClient;
	@FXML private TextField textFieldVilleClient;
	@FXML private TextField textFieldPaysClient;
	@FXML private Button buttonValider;
	@FXML private Button buttonReturn;
	@FXML private Label labelAffichage;

	@FXML public void initialize() throws SQLException {
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		buttonValider.setDisable(true);
	}
	
	@FXML public void majLabelAffichage(ActionEvent e) throws SQLException, IOException{
		DAOFactory dao = DAOFactory.getDAOFactory(Persistance.MYSQL);
		String nom = textFieldNomClient.getText().trim();
		String prenom = textFieldPrenomClient.getText().trim();
		String id = textFieldIdentifiantClient.getText().trim();
		String mdp = textFieldMdpClient.getText().trim();
		String adrnum = textFieldAdrNumClient.getText().trim();
		String adrvoie = textFieldAdrVoieClient.getText().trim();
		String codepostal = textFieldCodePostalClient.getText().trim();
		String ville = textFieldVilleClient.getText().trim();
		String pays = textFieldPaysClient.getText().trim();

		labelAffichage.setText(nom + "," + prenom + "," + id + "," + mdp + "," + adrnum + "," + adrvoie + "," + codepostal + "," + ville + "," + pays);
		labelAffichage.setStyle("-fx-text-fill: black; -fx-font-size: 11pt;");
		Client client = new Client(0, nom, prenom, id, mdp, adrnum, adrvoie, codepostal, ville, pays);
		dao.getClientDAO().create(client);
		
		clickOnReturn(e);
		}
	
	@FXML public void keyReleasedProperty() {
		String nom = textFieldNomClient.getText();
		String prenom = textFieldPrenomClient.getText();
		String id = textFieldIdentifiantClient.getText();
		String mdp = textFieldMdpClient.getText();
		String adrnum = textFieldAdrNumClient.getText();
		String adrvoie = textFieldAdrVoieClient.getText();
		String codepostal = textFieldCodePostalClient.getText();
		String ville = textFieldVilleClient.getText();
		String pays = textFieldPaysClient.getText();

		boolean isDisabled = (nom.isEmpty() || nom.trim().isEmpty() || prenom.isEmpty() || prenom.trim().isEmpty() ||
							  id.isEmpty() || id.trim().isEmpty() || mdp.isEmpty() || mdp.trim().isEmpty() ||
							  adrnum.isEmpty() || adrnum .trim().isEmpty() || adrvoie.isEmpty() || adrvoie.trim().isEmpty() ||
							  codepostal.isEmpty() || codepostal.trim().isEmpty() || ville.isEmpty() || ville.trim().isEmpty() ||
							  pays.isEmpty() || pays.trim().isEmpty());
		buttonValider.setDisable(isDisabled);
	}
	
	@FXML public void clickOnReturn(ActionEvent e) throws IOException {
		Parent Client = FXMLLoader.load(getClass().getResource("/projet/FXML/pageclient.fxml"));
		Scene Clientscene = new Scene(Client);
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		window.setScene(Clientscene);
		window.centerOnScreen();
		window.setTitle("Client");
		window.show();
	}
}
